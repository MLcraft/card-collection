package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.responses.UserInfoResponseDto;
import com.shizubro.cardcollection.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/users")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/getUserByDiscord/{discordId}")
    public UserInfoResponseDto getUserIdByDiscordId(@PathVariable Long discordId) {
        UserInfoResponseDto userInfoResponse = new UserInfoResponseDto();
        userInfoResponse.setId(this.userInfoService.getOrCreateUserIdByDiscordId(discordId).toString());
        return userInfoResponse;
    }

    @GetMapping("/getDiscordByUser/{userId}")
    public UserInfoResponseDto getDiscordIdByUserId(@PathVariable UUID userId) {
        UserInfoResponseDto userInfoResponse = new UserInfoResponseDto();
        userInfoResponse.setId(this.userInfoService.getDiscordIdByUserId(userId).toString());
        return userInfoResponse;
    }
}