package com.shizubro.cardcollection.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCardEntryResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("borrower_id")
    private String borrowerId;

    @JsonProperty("count")
    private Long count;
}
