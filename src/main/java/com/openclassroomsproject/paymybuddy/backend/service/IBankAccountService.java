package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import java.util.List;

public interface IBankAccountService {

    List<BankAccount> findAllBankAccount();
}