package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.repository.UserAccountRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password) {
        return userAccountRepository.findUserAccountByEmailAndPassword(email, password);
    }
}