package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import java.util.Optional;

public interface IUserAccountService {

    Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password);
}