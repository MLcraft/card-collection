package com.shizubro.cardcollection.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SearchScryfallCardsRequestDto {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @Size(min = 1, max = 10)
    @JsonProperty("set_code")
    private String setCode;

    @JsonProperty("collector_number")
    @Size(min = 1, max = 10)
    private String collectorNumber;
}
