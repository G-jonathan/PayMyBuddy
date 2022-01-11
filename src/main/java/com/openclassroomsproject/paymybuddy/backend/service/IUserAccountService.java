package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import java.util.Optional;

public interface IUserAccountService {

    void adminAccountProvision(double amount);

    UserAccount findUserAccountByEmail(String email);

    double getAccountBalance();

    boolean isUserBalanceSufficient(double amountRequested);

    boolean createUserAccount(UserAccount userAccount);

    Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password);

    void updateUserAccount(UserAccount userAccount);

    void deleteUserAccount(UserAccount userAccount);

    boolean emailUserAlreadyExist(String email);
}