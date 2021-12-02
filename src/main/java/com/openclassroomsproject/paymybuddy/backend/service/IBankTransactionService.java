package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.BankTransaction;
import java.util.List;

public interface IBankTransactionService {

    void addBankTransaction(BankTransaction bankTransaction);

    List<BankTransaction> findAllBankTransactionByUserAccountEmail(String userAccountEmail);

}