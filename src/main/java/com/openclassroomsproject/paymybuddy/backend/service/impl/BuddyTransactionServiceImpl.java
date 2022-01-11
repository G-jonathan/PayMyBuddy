package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.BuddyTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBuddyTransactionService;
import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.openclassroomsproject.paymybuddy.backend.service.ServiceUtils;
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
        String transmitterEmail = securityProvider.getAuthenticatedUser().getUsername();
        String beneficiaryEmail = visibleBuddyTransaction.getConnexionEmail();
        UserAccount transmitterAccount = userAccountService.findUserAccountByEmail(transmitterEmail);
        UserAccount beneficiaryAccount = userAccountService.findUserAccountByEmail(beneficiaryEmail);
        double transmitterAmountBeforeModification = transmitterAccount.getBalance();
        double beneficiaryAmountBeforeModification = beneficiaryAccount.getBalance();
        ServiceUtils utils = new ServiceUtils();
        double amountWithoutCharges = visibleBuddyTransaction.getAmount();
        double charges = utils.calculateCharges(amountWithoutCharges);
        double amountWithCharges = utils.calculateFinalAmountWithCharges(amountWithoutCharges);
        if (!userAccountService.isUserBalanceSufficient(amountWithCharges) || amountWithCharges == 0) {
            return false;
        }
        transmitterAccount.setBalance(transmitterAmountBeforeModification - amountWithCharges);
        beneficiaryAccount.setBalance(beneficiaryAmountBeforeModification + amountWithoutCharges);
        try {
            userAccountService.updateUserAccount(transmitterAccount);
            userAccountService.updateUserAccount(beneficiaryAccount);
            userAccountService.adminAccountProvision(charges);
        } catch (Exception e) {
            return false;
        }
        BuddyTransaction buddyTransaction = new BuddyTransaction();
        int connexionId = connexionService.findConnexionIdByUserAccountEmailAndConnexionEmail(transmitterEmail, beneficiaryEmail);
        buddyTransaction.setUserAccountEmail(transmitterEmail);
        buddyTransaction.setConnexionId(connexionId);
        buddyTransaction.setDate(visibleBuddyTransaction.getDate());
        buddyTransaction.setAmount(amountWithoutCharges);
        buddyTransaction.setDescription("Payment of " + amountWithoutCharges + " euros to " + beneficiaryEmail);
        buddyTransaction.setCharges(charges);
        buddyTransactionRepository.save(buddyTransaction);
        return true;
    }

    @Override
    public List<VisibleBuddyTransaction> findAllUserBuddyTransactions() {
        String email = securityProvider.getAuthenticatedUser().getUsername();
        List<BuddyTransaction> buddyTransactionList = buddyTransactionRepository.findAllUserBuddyTransactionsByUserAccountEmail(email);
        List<VisibleBuddyTransaction> visibleBuddyTransactionList = new ArrayList<>();
        for (BuddyTransaction buddyTransaction : buddyTransactionList) {
            VisibleBuddyTransaction visibleBuddyTransaction = new VisibleBuddyTransaction();
            visibleBuddyTransaction.setCharges(buddyTransaction.getCharges());
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