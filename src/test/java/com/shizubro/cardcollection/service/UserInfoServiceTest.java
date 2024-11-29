package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.model.User;
import com.shizubro.cardcollection.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInfoServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserInfoService userInfoService;

    private User user;
    private Long discordId;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        discordId = 123456789L;
        userId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        user.setDiscordId(discordId);
    }

    @Test
    void testGetOrCreateUserIdByDiscordId_UserExists() {
        when(userRepository.getUserByDiscordId(discordId)).thenReturn(user);
        UUID result = userInfoService.getOrCreateUserIdByDiscordId(discordId);
        assertEquals(userId, result);
        verify(userRepository, never()).save(any(User.class)); // No new user should be created
    }

    @Test
    void testGetOrCreateUserIdByDiscordId_UserDoesNotExist() {
        when(userRepository.getUserByDiscordId(discordId)).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);
        UUID result = userInfoService.getOrCreateUserIdByDiscordId(discordId);
        assertEquals(userId, result);
        verify(userRepository, times(1)).save(any(User.class)); // New user should be created and saved
    }

    @Test
    void testGetDiscordIdByUserId_UserExists() {
        when(userRepository.getUserById(userId)).thenReturn(user);
        Long result = userInfoService.getDiscordIdByUserId(userId);
        assertEquals(discordId, result);
    }

    @Test
    void testGetDiscordIdByUserId_UserDoesNotExist() {
        when(userRepository.getUserById(userId)).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userInfoService.getDiscordIdByUserId(userId);
        });

        assertEquals("No users found for given discordId", exception.getMessage());
    }
}
