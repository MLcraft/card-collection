package com.shizubro.cardcollection.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScryfallCardResponseDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("set_code")
    private String setCode;

    @JsonProperty("collector_number")
    private String collectorNumber;

    @JsonProperty("card_image_url")
    private String cardImageUrl;
}