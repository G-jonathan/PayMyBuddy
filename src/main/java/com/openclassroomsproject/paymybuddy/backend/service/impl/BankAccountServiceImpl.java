package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import com.openclassroomsproject.paymybuddy.backend.repository.BankAccountRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBankAccountService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements IBankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private SecurityProvider securityProvider;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, SecurityProvider securityProvider) {
        this.bankAccountRepository = bankAccountRepository;
        this.securityProvider = securityProvider;
    }

    @Override
    public boolean addBankAccount(BankAccount bankAccount) {
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<BankAccount> bankAccountExist = bankAccountRepository.findBankAccountByUserAccountEmail(userAccountEmail);
        if (bankAccountExist.isPresent()) {
            return false;
        }
        bankAccount.setUserAccountEmail(userAccountEmail);
        bankAccountRepository.save(bankAccount);
        return true;
    }

    @Override
    public BankAccount findBankAccountByUserAccountEmail() {
        BankAccount bankAccount = new BankAccount();
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<BankAccount> ifBankAccountExist = bankAccountRepository.findBankAccountByUserAccountEmail(userAccountEmail);
        if (ifBankAccountExist.isPresent()) {
            bankAccount = ifBankAccountExist.get();
        }
        return bankAccount;
    }

    @Override
    public List<BankAccount> findBankAccountsByUserAccountEmail() {
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        return bankAccountRepository.findAllBankAccountByUserAccountEmail(userAccountEmail);
    }

    @Override
    public boolean checkIfUserHasRegisteredABankAccount() {
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<BankAccount> isAccountPresent = bankAccountRepository.findBankAccountByUserAccountEmail(userAccountEmail);
        return isAccountPresent.isPresent();
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteABankAccount() {
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        bankAccountRepository.deleteBankAccountByUserAccountEmail(userAccountEmail);
    }
}