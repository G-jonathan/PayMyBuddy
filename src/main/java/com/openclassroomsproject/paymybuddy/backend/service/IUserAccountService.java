package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import java.util.Optional;

public interface IUserAccountService {

    void createUserAccount(UserAccount userAccount);

    Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password);

    void updateUserAccount(UserAccount userAccount);

    void deleteUserAccount(UserAccount userAccount);

    boolean emailUserAlreadyExist(String email);
}