package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.responses.UserCardEntryResponseDto;
import com.shizubro.cardcollection.model.UserCardEntry;
import org.springframework.stereotype.Component;

@Component
public class UserCardEntryMapper {
    public UserCardEntryResponseDto entityToResponseDto(UserCardEntry entity) {
        UserCardEntryResponseDto cardEntryDto = new UserCardEntryResponseDto();

        cardEntryDto.setId(entity.getId());
        cardEntryDto.setCardName(entity.getCard().getName());
        cardEntryDto.setCardSetCode(entity.getCard().getSetcode());
        cardEntryDto.setCardNumber(entity.getCard().getCollectorNumber());
        cardEntryDto.setCardImageUrl(entity.getCard().getImageUri());
        cardEntryDto.setOwnerId(entity.getOwner().getDiscordId().toString());
        cardEntryDto.setBorrowerId(entity.getBorrower().getDiscordId().toString());
        cardEntryDto.setCount(entity.getCount());

        return cardEntryDto;
    }
}
