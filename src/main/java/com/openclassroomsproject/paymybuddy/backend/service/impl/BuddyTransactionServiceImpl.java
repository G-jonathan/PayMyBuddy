package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.BuddyTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBuddyTransactionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuddyTransactionServiceImpl implements IBuddyTransactionService {
    private final BuddyTransactionRepository buddyTransactionRepository;

    public BuddyTransactionServiceImpl(BuddyTransactionRepository buddyTransactionRepository) {
        this.buddyTransactionRepository = buddyTransactionRepository;
    }

    @Override
    public List<BuddyTransaction> findAllBuddyTransaction() {
        return buddyTransactionRepository.findAll();
    }

    @Override
    public List<BuddyTransaction> findAllBuddyTransactionByUserAccountEmail(String userAccountEmail) {
        return buddyTransactionRepository.findAllBuddyTransactionByUserAccountEmail(userAccountEmail);
    }
}