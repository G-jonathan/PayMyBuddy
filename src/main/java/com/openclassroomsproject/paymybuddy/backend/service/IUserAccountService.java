package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import java.util.List;

public interface IUserAccountService {

    List<UserAccount> findAllUserAccount();
}