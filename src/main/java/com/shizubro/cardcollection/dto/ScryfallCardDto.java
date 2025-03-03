package com.shizubro.cardcollection.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shizubro.cardcollection.enums.Layout;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    @JsonProperty("digital")
    private Boolean isDigital;

    @JsonProperty("layout")
    private Layout layout;

    @JsonProperty("image_uris")
    private ImageUris imageUris;

    @JsonProperty("card_faces")
    private List<CardFaces> cardFaces;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
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

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CardFaces {

        @JsonProperty("image_uris")
        private ImageUris imageUris;
    }
}
