package com.shizubro.cardcollection.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LendCardsRequestDto {

    @NotNull
    @JsonProperty("card_id")
    private String cardId;

    @NotNull
    @JsonProperty("owner_id")
    private String ownerId;

    @NotNull
    @JsonProperty("borrower_id")
    private String borrowerId;

    @NotNull
    @Min(1)
    @JsonProperty("count")
    private Long count;
}
