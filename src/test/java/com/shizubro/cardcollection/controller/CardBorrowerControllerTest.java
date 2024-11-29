package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.requests.LendCardsRequestDto;
import com.shizubro.cardcollection.mapper.UserCardEntryMapper;
import com.shizubro.cardcollection.service.CardBorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardBorrowerController.class)
public class CardBorrowerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardBorrowerService cardBorrowerService;

    @MockBean
    private UserCardEntryMapper userCardEntryMapper;

    private LendCardsRequestDto lendCardsRequestDto;

    @BeforeEach
    void setUp() {
        lendCardsRequestDto = new LendCardsRequestDto();
        lendCardsRequestDto.setOwnerId(UUID.randomUUID().toString());
        lendCardsRequestDto.setBorrowerId(UUID.randomUUID().toString());
        lendCardsRequestDto.setCardId(UUID.randomUUID().toString());
        lendCardsRequestDto.setCount(3L);
    }

    @Test
    void testLendCardsFromOwnerToUser() throws Exception {
        doNothing().when(cardBorrowerService).lendCardFromOwnerToUser(any(), any(), any(), anyLong());

        mockMvc.perform(post("/lending/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.asJsonString(lendCardsRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("lendCardsFromOwnerToUser"));

        verify(cardBorrowerService, times(1)).lendCardFromOwnerToUser(
                UUID.fromString(lendCardsRequestDto.getOwnerId()),
                UUID.fromString(lendCardsRequestDto.getBorrowerId()),
                UUID.fromString(lendCardsRequestDto.getCardId()),
                lendCardsRequestDto.getCount()
        );
    }

    @Test
    void testReturnCardsFromUserToOwner() throws Exception {
        doNothing().when(cardBorrowerService).returnCardFromUserToOwner(any(), any(), any(), anyLong());

        mockMvc.perform(post("/lending/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utility.asJsonString(lendCardsRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("returnCardsFromUserToOwner"));

        verify(cardBorrowerService, times(1)).returnCardFromUserToOwner(
                UUID.fromString(lendCardsRequestDto.getOwnerId()),
                UUID.fromString(lendCardsRequestDto.getBorrowerId()),
                UUID.fromString(lendCardsRequestDto.getCardId()),
                lendCardsRequestDto.getCount()
        );
    }

    @Test
    void testGetCardsLentByOwner() throws Exception {
        when(cardBorrowerService.getLentOutCardsForOwner(any())).thenReturn(new ArrayList<>());  // Return an empty list

        mockMvc.perform(get("/lending/lentCards/{ownerId}", UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(cardBorrowerService, times(1)).getLentOutCardsForOwner(any());
    }

    @Test
    void testGetCardsBorrowedByUser() throws Exception {

        when(cardBorrowerService.getCardsBorrowedByUser(any())).thenReturn(new ArrayList<>());  // Return an empty list
        mockMvc.perform(get("/lending/borrowedCards/{borrowerId}", UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
        verify(cardBorrowerService, times(1)).getCardsBorrowedByUser(any());
    }
}

