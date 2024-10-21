package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.ScryfallCardDto;
import com.shizubro.cardcollection.model.ScryfallCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScryfallCardMapper {

    public ScryfallCard dtoToEntity(ScryfallCardDto dto) {
        String cardImageUrl = null;
        if (dto.getImageUris() != null) {
            cardImageUrl = dto.getImageUris().getPng();
        } else {
            log.info("image uris is null");
            if (dto.getCardFaces() != null) {
                if (!dto.getCardFaces().isEmpty()) {
                    log.info("Has card faces");
                    cardImageUrl = dto.getCardFaces().get(0).getImageUris().getPng();
                }
            }
        }
        ScryfallCard card = new ScryfallCard();
        card.setId(dto.getId());
        card.setName(dto.getName());
        card.setSetcode(dto.getSetcode());
        card.setCollectorNumber(dto.getCollectorNumber());
        // TODO: MDFCs and such can have card faces instead of just card image url so check for those as well and take front face if no image url
        card.setImageUri(cardImageUrl);
        return card;
    }
}
