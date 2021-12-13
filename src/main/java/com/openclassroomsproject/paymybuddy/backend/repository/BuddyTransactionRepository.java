package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuddyTransactionRepository extends JpaRepository<BuddyTransaction, Integer> {

    List<BuddyTransaction> findAllUserBuddyTransactionsByUserAccountEmail(String userAccountEmail);
}