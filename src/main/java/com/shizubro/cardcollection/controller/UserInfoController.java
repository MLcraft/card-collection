package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.responses.UserCardEntryResponseDto;
import com.shizubro.cardcollection.dto.responses.UserInfoResponseDto;
import com.shizubro.cardcollection.service.UserInfoService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserInfoResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error occured while getting user by discord id",
                    content = @Content) })
    @Tag(name = "GET user info", description = "GET methods of User data APIs")
    @GetMapping("/getUserByDiscord/{discordId}")
    public UserInfoResponseDto getUserIdByDiscordId(@PathVariable Long discordId) {
        UserInfoResponseDto userInfoResponse = new UserInfoResponseDto();
        userInfoResponse.setId(this.userInfoService.getOrCreateUserIdByDiscordId(discordId).toString());
        return userInfoResponse;
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserInfoResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error occured while getting user discord by user id",
                    content = @Content) })
    @Tag(name = "GET user info", description = "GET methods of User data APIs")
    @GetMapping("/getDiscordByUser/{userId}")
    public UserInfoResponseDto getDiscordIdByUserId(@PathVariable UUID userId) {
        UserInfoResponseDto userInfoResponse = new UserInfoResponseDto();
        userInfoResponse.setId(this.userInfoService.getDiscordIdByUserId(userId).toString());
        return userInfoResponse;
    }
}