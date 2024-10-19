package com.shizubro.cardcollection.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BulkDataDownloadInfoDto(
        @JsonProperty("download_uri")
        String downloadUri,
        @JsonProperty("updated_at")
        Instant updatedAt
        ) {
}
