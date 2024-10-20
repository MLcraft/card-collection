package com.shizubro.cardcollection.publisher;

import com.shizubro.cardcollection.dto.ScryfallCardDto;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ScryfallCardDataPublisher {
    public static final String SCRYFALL_CARD_DATA_PRODUCER_OUT = "card-data-producer-out-0";
    protected static final String ROUTING_KEY_HEADER = "X-Routingkey";
    protected static final String SCRYFALL_CARD_DATA_ROUTING_KEY_VALUE = "scryfall-card-message-routing-key";
    private final StreamBridge streamBridge;

    public ScryfallCardDataPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public boolean publish(ScryfallCardDto message) {
        return streamBridge.send(SCRYFALL_CARD_DATA_PRODUCER_OUT, buildMessage(message));
    }

    private Message<ScryfallCardDto> buildMessage(ScryfallCardDto message) {

        MessageBuilder<ScryfallCardDto> builder = MessageBuilder.withPayload(message);
        builder.setHeader(ROUTING_KEY_HEADER, SCRYFALL_CARD_DATA_ROUTING_KEY_VALUE);
        return builder.build();

    }
}
