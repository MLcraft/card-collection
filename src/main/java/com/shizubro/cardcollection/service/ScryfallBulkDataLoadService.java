package com.shizubro.cardcollection.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shizubro.cardcollection.dto.BulkDataDownloadInfoDto;
import com.shizubro.cardcollection.dto.ScryfallCardDto;
import com.shizubro.cardcollection.mapper.ScryfallCardMapper;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.publisher.ScryfallCardDataPublisher;
import com.shizubro.cardcollection.receiver.ScryfallCardDataReceiver;
import com.shizubro.cardcollection.repository.ScryfallCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ScryfallBulkDataLoadService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ScryfallCardMapper scryfallCardMapper;
    private final ScryfallCardRepository scryfallCardRepository;
    private final ScryfallCardDataPublisher scryfallCardDataPublisher;

    @Autowired
    public ScryfallBulkDataLoadService(RestTemplate restTemplate, ObjectMapper objectMapper, ScryfallCardMapper scryfallCardMapper, ScryfallCardRepository scryfallCardRepository, ScryfallCardDataPublisher scryfallCardDataPublisher) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.scryfallCardMapper = scryfallCardMapper;
        this.scryfallCardRepository = scryfallCardRepository;
        this.scryfallCardDataPublisher = scryfallCardDataPublisher;
    }

    public BulkDataDownloadInfoDto getBulkDataDownloadInfo() {

        return restTemplate.getForObject("https://api.scryfall.com/bulk-data/default-cards", BulkDataDownloadInfoDto.class);
    }

    // scheduled every day
    @Scheduled(fixedRate = 1000*60*60*24)
    public void initializeBulkDataImport() {
        log.info("Fetching bulk data download info dto");
        BulkDataDownloadInfoDto latestBulkDataDownloadInfo = this.getBulkDataDownloadInfo();
        if (latestBulkDataDownloadInfo == null) {
            log.error("bulk data response is null");
        } else {
            // TODO: check cache for last updated bulk data
            log.info("Fetching bulk data download uri");
            String bulkDataDownloadUri = latestBulkDataDownloadInfo.downloadUri();

            log.info("Fetching scryfall card dtos");
            List<ScryfallCardDto> scryfallCardDtos = this.getBulkCardDataFromJSON(bulkDataDownloadUri);
            log.info("About to persist dtos");
            for (ScryfallCardDto cardDto: scryfallCardDtos) {
                log.info("Publishing dto");
                boolean published = this.scryfallCardDataPublisher.publish(cardDto);
                if (published) {
                    log.info("Published a dto");
                } else {
                    log.error("Publish failed");
                }
                // TODO: enable all to run, don't return after just first one (testing purposes)
                return;
            }
        }
    }

    public List<ScryfallCardDto> getBulkCardDataFromJSON(String bulkDataDownloadUri) {
        try {
            URL downloadUrl = new URL(bulkDataDownloadUri);
            return Arrays.asList(objectMapper.readValue(downloadUrl, ScryfallCardDto[].class));
        } catch (MalformedURLException e) {
            log.error("error in format of download URI");
        } catch (IOException e) {
            log.error("error processing JSON download data");
        }
        return new ArrayList<>();
    }

    public void persistCardData(ScryfallCardDto cardDto) {
        // map card dto to entity
        ScryfallCard card = this.scryfallCardMapper.dtoToEntity(cardDto);
        // save entity to db
        this.scryfallCardRepository.save(card);
    }
}