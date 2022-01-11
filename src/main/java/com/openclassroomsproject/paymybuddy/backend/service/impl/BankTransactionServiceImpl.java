package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.*;
import com.openclassroomsproject.paymybuddy.backend.repository.BankTransactionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBankAccountService;
import com.openclassroomsproject.paymybuddy.backend.service.IBankTransactionService;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.openclassroomsproject.paymybuddy.backend.service.ServiceUtils;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankTransactionServiceImpl implements IBankTransactionService {
    private final BankTransactionRepository bankTransactionRepository;
    private final IBankAccountService bankAccountService;
    private final SecurityProvider securityProvider;
    private final IUserAccountService userAccountService;

    public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository, IBankAccountService bankAccountService, SecurityProvider securityProvider, IUserAccountService userAccountService) {
        this.bankTransactionRepository = bankTransactionRepository;
        this.bankAccountService = bankAccountService;
        this.securityProvider = securityProvider;
        this.userAccountService = userAccountService;
    }

    @Override
    public boolean addBankTransaction(VisibleBuddyTransaction visibleTransaction) {
        final String DEPOSIT = "BANK DEPOSIT";
        final String WITHDRAWAL = "BANK WITHDRAWAL";
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        BankAccount bankAccount = bankAccountService.findBankAccountByUserAccountEmail();
        UserAccount userAccount = userAccountService.findUserAccountByEmail(userAccountEmail);
        double actualAccountBalance = userAccount.getBalance();
        ServiceUtils utils = new ServiceUtils();
        double amountWithoutCharges = visibleTransaction.getAmount();
        double charges = utils.calculateCharges(amountWithoutCharges);
        double finalAmount = utils.calculateFinalAmountWithCharges(amountWithoutCharges);
        BankTransaction bankTransaction = new BankTransaction();
        String transactionType = "";
        if (visibleTransaction.getConnexionEmail().equals(DEPOSIT)) {
            if (!userAccountService.isUserBalanceSufficient(finalAmount)) {
                return false;
            }
            bankTransaction.setTransactionType(BankTransaction.BankTransactionType.Deposit);
            transactionType = DEPOSIT;
            userAccount.setBalance(actualAccountBalance - finalAmount);
        }
        if (visibleTransaction.getConnexionEmail().equals(WITHDRAWAL)) {
            bankTransaction.setTransactionType(BankTransaction.BankTransactionType.WithDrawal);
            transactionType = WITHDRAWAL;
            userAccount.setBalance(actualAccountBalance + amountWithoutCharges);
        }
        try {
            userAccountService.updateUserAccount(userAccount);
            userAccountService.adminAccountProvision(charges);
        } catch (Exception e) {
            return false;
        }
        bankTransaction.setUserAccountEmail(userAccountEmail);
        bankTransaction.setBankAccountId(bankAccount.getId());
        bankTransaction.setDate(visibleTransaction.getDate());
        bankTransaction.setDescription(transactionType + " (n° " + bankAccount.getIban() + ")");
        bankTransaction.setAmount(amountWithoutCharges);
        bankTransaction.setCharges(charges);
        bankTransactionRepository.save(bankTransaction);
        return true;
    }

    @Override
    public List<VisibleBuddyTransaction> findAllBankTransactionUser() {
        String email = securityProvider.getAuthenticatedUser().getUsername();
        List<BankTransaction> bankTransactionList = bankTransactionRepository.findAllBankTransactionByUserAccountEmail(email);
        List<VisibleBuddyTransaction> visibleTransactionList = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactionList) {
            VisibleBuddyTransaction visibleTransaction = new VisibleBuddyTransaction();
            visibleTransaction.setCharges(bankTransaction.getCharges());
            visibleTransaction.setId(bankTransaction.getId());
            visibleTransaction.setAmount(bankTransaction.getAmount());
            visibleTransaction.setDescription(bankTransaction.getDescription());
            visibleTransaction.setDate(bankTransaction.getDate());
            visibleTransaction.setConnexionEmail(String.valueOf(bankTransaction.getTransactionType()));
            visibleTransactionList.add(visibleTransaction);
        }
        return visibleTransactionList;
    }
}