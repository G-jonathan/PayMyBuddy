package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import java.util.List;

public interface IBankAccountService {

    void addBankAccount(BankAccount bankAccount);

    List<BankAccount> findBankAccountsByUserAccountEmail(String userAccountEmail);

    void updateBankAccount(BankAccount bankAccount);

    void deleteABankAccount(BankAccount bankAccount);
}