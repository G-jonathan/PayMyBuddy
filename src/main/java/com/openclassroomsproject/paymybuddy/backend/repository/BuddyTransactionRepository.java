package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuddyTransactionRepository extends JpaRepository<BuddyTransaction, Integer> {
}
