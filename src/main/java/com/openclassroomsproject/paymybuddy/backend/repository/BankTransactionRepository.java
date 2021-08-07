package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Integer> {
}
