package com.shizubro.cardcollection.receiver;

import com.shizubro.cardcollection.dto.ScryfallCardDto;
import com.shizubro.cardcollection.service.ScryfallBulkDataLoadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ScryfallCardDataReceiverTest {

    @Mock
    private ScryfallBulkDataLoadService scryfallService;

    @Mock
    private Message<ScryfallCardDto> message;

    @InjectMocks
    private ScryfallCardDataReceiver scryfallCardDataReceiver;

    private ScryfallCardDto cardDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cardDto = new ScryfallCardDto();
        when(message.getPayload()).thenReturn(cardDto);
    }

    @Test
    void testHandle() {
        scryfallCardDataReceiver.handle(message);
        verify(scryfallService, times(1)).persistCardData(cardDto);
        verify(message, times(1)).getPayload();
    }

    @Test
    void testCardDataReceiver() {
        Consumer<Message<ScryfallCardDto>> consumer = scryfallCardDataReceiver.cardDataReceiver();
        assertNotNull(consumer);

        // Simulate receiving a message
        consumer.accept(message);
        verify(scryfallService, times(1)).persistCardData(cardDto);
    }
}

