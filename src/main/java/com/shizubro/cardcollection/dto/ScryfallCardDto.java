package com.shizubro.cardcollection.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ScryfallCardDto(
        UUID id,
        String name
) {
}
