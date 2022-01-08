package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.BuddyTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBuddyTransactionService;
import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuddyTransactionServiceImpl implements IBuddyTransactionService {
    private final SecurityProvider securityProvider;
    private final BuddyTransactionRepository buddyTransactionRepository;
    private final IConnexionService connexionService;
    private final IUserAccountService userAccountService;


    public BuddyTransactionServiceImpl(SecurityProvider securityProvider, BuddyTransactionRepository buddyTransactionRepository, IConnexionService connexionService, IUserAccountService userAccountService) {
        this.securityProvider = securityProvider;
        this.buddyTransactionRepository = buddyTransactionRepository;
        this.connexionService = connexionService;
        this.userAccountService = userAccountService;
    }

    @Override
    public boolean addBuddyTransaction(VisibleBuddyTransaction visibleBuddyTransaction) {
        boolean updateAccountsAmount = userAccountService.updateUsersAccountsBeforeSavingTheTransaction(visibleBuddyTransaction, false);
        if (updateAccountsAmount) {
            try {
                BuddyTransaction buddyTransaction = new BuddyTransaction();
                String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
                double amount = visibleBuddyTransaction.getAmount();
                String connexionEmail = visibleBuddyTransaction.getConnexionEmail();
                int connexionId = connexionService.findConnexionIdByUserAccountEmailAndConnexionEmail(userAccountEmail, connexionEmail);
                buddyTransaction.setAmount(amount);
                buddyTransaction.setDate(visibleBuddyTransaction.getDate());
                buddyTransaction.setDescription("Payment of " + amount + " euros to " + connexionEmail);
                buddyTransaction.setConnexionId(connexionId);
                buddyTransaction.setUserAccountEmail(userAccountEmail);
                buddyTransaction.setCharges(0);
                buddyTransactionRepository.save(buddyTransaction);
            } catch (Exception e) {
                userAccountService.updateUsersAccountsBeforeSavingTheTransaction(visibleBuddyTransaction, true);
                return false;
            }
            return true;
        } else {
            return false;
        }
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
            String connexionEmail = connexionService.findConnexionEmailById(buddyTransaction.getConnexionId());
            visibleBuddyTransaction.setConnexionEmail(connexionEmail);
            visibleBuddyTransactionList.add(visibleBuddyTransaction);
        }
        return visibleBuddyTransactionList;
    }

    @Override
    public void deleteBuddyTransaction(BuddyTransaction buddyTransaction) {
        buddyTransactionRepository.delete(buddyTransaction);
    }
}