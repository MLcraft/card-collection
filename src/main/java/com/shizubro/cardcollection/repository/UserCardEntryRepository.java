package com.shizubro.cardcollection.repository;

import com.shizubro.cardcollection.model.UserCardEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCardEntryRepository extends JpaRepository<UserCardEntry, Long> {
  List<UserCardEntry> findAllByOwnerId(UUID ownerId);
  List<UserCardEntry> findAllByBorrowerId(UUID borrowerId);
  List<UserCardEntry> findByCardIdAndOwnerId(UUID cardId, UUID ownerId);
  UserCardEntry findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(UUID ownerId, UUID borrowerId, UUID cardId);
}