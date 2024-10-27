package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.responses.UserCardEntryResponseDto;
import com.shizubro.cardcollection.model.UserCardEntry;
import org.springframework.stereotype.Component;

@Component
public class UserCardEntryMapper {
    public UserCardEntryResponseDto entityToResponseDto(UserCardEntry entity) {
        UserCardEntryResponseDto cardEntryDto = new UserCardEntryResponseDto();

        cardEntryDto.setId(entity.getId());
        cardEntryDto.setCardId(entity.getCard().getId().toString());
        cardEntryDto.setOwnerId(entity.getOwner().getId().toString());
        cardEntryDto.setBorrowerId(entity.getBorrower().getId().toString());
        cardEntryDto.setCount(entity.getCount());

        return cardEntryDto;
    }
}
