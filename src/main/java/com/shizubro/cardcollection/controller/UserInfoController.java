package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RestController()
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/getUserByDiscord")
    public String getUserIdByDiscordId(@RequestParam Long discordId) {
        return this.userInfoService.getOrCreateUserIdByDiscordId(discordId).toString();
    }
}
