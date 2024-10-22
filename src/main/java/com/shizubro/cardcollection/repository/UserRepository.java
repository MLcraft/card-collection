package com.shizubro.cardcollection.repository;

import com.shizubro.cardcollection.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User getUserByDiscordId(Long discordId);
}