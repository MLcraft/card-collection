package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.ScryfallCardDto;
import com.shizubro.cardcollection.model.ScryfallCard;
import org.springframework.stereotype.Component;

@Component
public class ScryfallCardMapper {

    public ScryfallCard dtoToEntity(ScryfallCardDto dto) {
        String cardImageUrl = null;
        if (dto.getImageUris() != null) {
            cardImageUrl = dto.getImageUris().getPng();
        }
        ScryfallCard card = new ScryfallCard();
        card.setId(dto.getId());
        card.setName(dto.getName());
        card.setSetcode(dto.getSetcode());
        card.setCollectorNumber(dto.getCollectorNumber());
        card.setImageUri(cardImageUrl);
        return card;
    }
}
