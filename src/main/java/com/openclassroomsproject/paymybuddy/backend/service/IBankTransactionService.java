package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import java.util.List;

public interface IBankTransactionService {

    boolean addBankTransaction(VisibleBuddyTransaction visibleBuddyTransaction);

    List<VisibleBuddyTransaction> findAllBankTransactionUser();
}