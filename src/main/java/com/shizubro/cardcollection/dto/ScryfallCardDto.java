package com.shizubro.cardcollection.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScryfallCardDto {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("set")
    private String setcode;

    @JsonProperty("collector_number")
    private String collectorNumber;

    @JsonProperty("image_uris")
    private ImageUris imageUris;

    @Getter
    @Setter
    public static class ImageUris {
        @JsonProperty("small")
        private String small;

        @JsonProperty("large")
        private String large;

        @JsonProperty("normal")
        private String normal;

        @JsonProperty("png")
        private String png;

        @JsonProperty("art_crop")
        private String artCrop;

        @JsonProperty("border_crop")
        private String borderCrop;
    }
}
