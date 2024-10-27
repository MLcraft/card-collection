package com.shizubro.cardcollection.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchScryfallCardsRequestDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("set_code")
    private String setCode;

    @JsonProperty("collector_number")
    private String collectorNumber;
}
