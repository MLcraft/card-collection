package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.ScryfallCardDto;
import com.shizubro.cardcollection.model.ScryfallCard;
import org.springframework.stereotype.Component;

@Component
public class ScryfallCardMapper {
    public ScryfallCardDto entityToDto(ScryfallCard card) {
        return new ScryfallCardDto(card.getId(), card.getName());
    }

    public ScryfallCard dtoToEntity(ScryfallCardDto dto) {
        ScryfallCard card = new ScryfallCard();
        card.setId(dto.id());
        card.setName(dto.name());
        return card;
    }
}
