package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import java.util.List;

public interface IBankAccountService {

    BankAccount findBankAccountByUserAccountEmail();

    boolean addBankAccount(BankAccount bankAccount);

    boolean checkIfUserHasRegisteredABankAccount();

    List<BankAccount> findBankAccountsByUserAccountEmail();

    void updateBankAccount(BankAccount bankAccount);

    void deleteABankAccount();
}