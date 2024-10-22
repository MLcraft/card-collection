package com.shizubro.cardcollection.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserCardEntryDto {
    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("borrower_id")
    private String borrowerId;

    @JsonProperty("count")
    private int count;
}
