package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.requests.SearchScryfallCardsRequestDto;
import com.shizubro.cardcollection.dto.responses.ScryfallCardResponseDto;
import com.shizubro.cardcollection.mapper.ScryfallCardMapper;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.service.ScryfallCardSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScryfallCardSearchController.class)
public class ScryfallCardSearchControllerTest {

    @MockBean
    private ScryfallCardSearchService scryfallCardSearchService;

    @MockBean
    private ScryfallCardMapper scryfallCardMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchScryfallCards_Success() throws Exception {

        SearchScryfallCardsRequestDto requestDto = new SearchScryfallCardsRequestDto();
        requestDto.setName("CardName");
        requestDto.setSetCode("SetCode");
        requestDto.setCollectorNumber("123");
        ScryfallCard mockCard = new ScryfallCard();
        ScryfallCardResponseDto mockResponseDto = new ScryfallCardResponseDto();
        mockResponseDto.setName("CardName");
        mockResponseDto.setSetCode("SetCode");
        when(scryfallCardSearchService.searchScryfallCardByNameSetNumber("CardName", "SetCode", "123"))
                .thenReturn(Collections.singletonList(mockCard));
        when(scryfallCardMapper.entityToResponseDto(mockCard)).thenReturn(mockResponseDto);

        mockMvc.perform(post("/scryfall/search")
                        .contentType("application/json")
                        .content(Utility.asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CardName"))
                .andExpect(jsonPath("$[0].set_code").value("SetCode"));
    }

    @Test
    public void testSearchScryfallCards_InternalServerError() throws Exception {

        SearchScryfallCardsRequestDto requestDto = new SearchScryfallCardsRequestDto();
        requestDto.setName("CardName");
        requestDto.setSetCode("SetCode");
        requestDto.setCollectorNumber("123");
        when(scryfallCardSearchService.searchScryfallCardByNameSetNumber("CardName", "SetCode", "123"))
                .thenThrow(new RuntimeException("Error occurred"));

        mockMvc.perform(post("/scryfall/search")
                        .contentType("application/json")
                        .content(Utility.asJsonString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSearchScryfallCards_BadRequest() throws Exception {
        mockMvc.perform(post("/scryfall/search")
                        .contentType("application/json")
                        .content("{}")) // Empty request body
                .andExpect(status().isBadRequest());
    }


}

