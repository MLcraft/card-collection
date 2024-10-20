package com.shizubro.cardcollection.receiver;

import com.shizubro.cardcollection.dto.ScryfallCardDto;
import com.shizubro.cardcollection.service.ScryfallBulkDataLoadService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class ScryfallCardDataReceiver {
    private final ScryfallBulkDataLoadService scryfallService;

    @Autowired
    public ScryfallCardDataReceiver(ScryfallBulkDataLoadService scryfallService) {
        this.scryfallService = scryfallService;
    }

    public void handle(@NotNull final Message<ScryfallCardDto> message) {
        log.info("Consume a message from queue");
        scryfallService.persistCardData(message.getPayload());
    }

    @Bean
    public Consumer<Message<ScryfallCardDto>> cardDataReceiver() {
        return payload -> handle(payload);
    }
}
