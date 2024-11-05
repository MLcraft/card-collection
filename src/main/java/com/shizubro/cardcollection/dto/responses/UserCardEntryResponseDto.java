package com.shizubro.cardcollection.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCardEntryResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("card_name")
    private String cardName;

    @JsonProperty("card_set_code")
    private String cardSetCode;

    @JsonProperty("card_number")
    private String cardNumber;

    @JsonProperty("card_image_url")
    private String cardImageUrl;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("borrower_id")
    private String borrowerId;

    @JsonProperty("count")
    private Long count;
}
