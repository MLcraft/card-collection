package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.UserCardEntryDto;
import com.shizubro.cardcollection.model.UserCardEntry;
import org.springframework.stereotype.Component;

@Component
public class UserCardEntryMapper {
    public UserCardEntryDto entityToDto(UserCardEntry entity) {
        UserCardEntryDto cardEntryDto = new UserCardEntryDto();

        cardEntryDto.setId(entity.getId());
        cardEntryDto.setCardId(entity.getCard().getId().toString());
        cardEntryDto.setOwnerId(entity.getOwner().getId().toString());
        cardEntryDto.setBorrowerId(entity.getBorrower().getId().toString());
        cardEntryDto.setCount(entity.getCount());

        return cardEntryDto;
    }
}
