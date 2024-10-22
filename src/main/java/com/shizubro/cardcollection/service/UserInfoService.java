package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.model.User;
import com.shizubro.cardcollection.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserInfoService {
    private final UserRepository userRepository;

    @Autowired
    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User createUserIdWithDiscordId(Long discordId) {
        User newUser = new User();
        newUser.setDiscordId(discordId);
        return this.userRepository.save(newUser);
    }

    public UUID getOrCreateUserIdByDiscordId(Long discordId) {
        User foundUser = this.userRepository.getUserByDiscordId(discordId);
        if (foundUser == null) {
            log.info("No users found for given discordId, creating new user");
            foundUser = this.createUserIdWithDiscordId(discordId);
        }
        return foundUser.getId();

    }
}
