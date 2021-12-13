package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.BuddyTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBuddyTransactionService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuddyTransactionServiceImpl implements IBuddyTransactionService {

    @Autowired
    private final SecurityProvider securityProvider = new SecurityProvider();
    private final BuddyTransactionRepository buddyTransactionRepository;

    public BuddyTransactionServiceImpl(BuddyTransactionRepository buddyTransactionRepository) {
        this.buddyTransactionRepository = buddyTransactionRepository;
    }

    @Override
    public void addBuddyTransaction(BuddyTransaction buddyTransaction) {
        buddyTransactionRepository.save(buddyTransaction);
    }

    @Override
    public List<BuddyTransaction> findAllBuddyTransactionByUserAccountEmail() {
        return buddyTransactionRepository.findAllBuddyTransactionByUserAccountEmail(securityProvider.getAuthenticatedUser().getUsername());
    }
}