package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.BuddyTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBuddyTransactionService;
import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuddyTransactionServiceImpl implements IBuddyTransactionService {
    private final SecurityProvider securityProvider;
    private final BuddyTransactionRepository buddyTransactionRepository;
    private final IConnexionService connexionService;


    public BuddyTransactionServiceImpl(SecurityProvider securityProvider, BuddyTransactionRepository buddyTransactionRepository, IConnexionService connexionService) {
        this.securityProvider = securityProvider;
        this.buddyTransactionRepository = buddyTransactionRepository;
        this.connexionService = connexionService;
    }

    @Override
    public void addBuddyTransaction(BuddyTransaction buddyTransaction) {
        buddyTransactionRepository.save(buddyTransaction);
    }

    @Override
    public List<VisibleBuddyTransaction> findAllUserBuddyTransactions() {
        String email = securityProvider.getAuthenticatedUser().getUsername();
        List<BuddyTransaction> buddyTransactionList = buddyTransactionRepository.findAllUserBuddyTransactionsByUserAccountEmail(email);
        List<VisibleBuddyTransaction> visibleBuddyTransactionList = new ArrayList<>();
        for (BuddyTransaction buddyTransaction : buddyTransactionList) {
            VisibleBuddyTransaction visibleBuddyTransaction = new VisibleBuddyTransaction();
            visibleBuddyTransaction.setId(buddyTransaction.getId());
            visibleBuddyTransaction.setAmount(buddyTransaction.getAmount());
            visibleBuddyTransaction.setDescription(buddyTransaction.getDescription());
            visibleBuddyTransaction.setDate(buddyTransaction.getDate());
            String connexionEmail = connexionService.findConnexionEmailById(buddyTransaction.getId());
            visibleBuddyTransaction.setConnexionEmail(connexionEmail);
            visibleBuddyTransactionList.add(visibleBuddyTransaction);
        }
        return visibleBuddyTransactionList;
    }
}