package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BankTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.BankTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBankTransactionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankTransactionServiceImpl implements IBankTransactionService {
    private final BankTransactionRepository bankTransactionRepository;

    public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @Override
    public List<BankTransaction> findAllBankTransaction() {
        return bankTransactionRepository.findAll();
    }
}