package com.shizubro.cardcollection.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoResponseDto {
    @JsonProperty("id")
    private String id;
}
