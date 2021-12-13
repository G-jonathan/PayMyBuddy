package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import java.util.List;

public interface IBuddyTransactionService {

    void addBuddyTransaction(BuddyTransaction buddyTransaction);

    List<BuddyTransaction> findAllBuddyTransactionByUserAccountEmail();
}