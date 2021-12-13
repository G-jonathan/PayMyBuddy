package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import com.openclassroomsproject.paymybuddy.backend.repository.BankAccountRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IBankAccountService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankAccountServiceImpl implements IBankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> findBankAccountsByUserAccountEmail(String userAccountEmail) {
        return bankAccountRepository.findAllBankAccountByUserAccountEmail(userAccountEmail);
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteABankAccount(BankAccount bankAccount) {
        bankAccountRepository.deleteById(bankAccount.getId());
    }
}