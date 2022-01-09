package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import java.util.List;

public interface IBuddyTransactionService {

    public void deleteBuddyTransaction(BuddyTransaction buddyTransaction);

    boolean addBuddyTransaction(VisibleBuddyTransaction visibleBuddyTransaction);

    List<VisibleBuddyTransaction> findAllUserBuddyTransactions();
}