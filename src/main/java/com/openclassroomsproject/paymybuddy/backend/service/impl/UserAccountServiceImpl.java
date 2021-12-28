package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.repository.UserAccountRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createUserAccount(UserAccount userAccount) {
        String passwordNotEncrypted = userAccount.getPassword();
        userAccount.setPassword(new BCryptPasswordEncoder().encode(passwordNotEncrypted));
        userAccount.setActivated(true);
        userAccountRepository.save(userAccount);
        // TODO create role_user
    }

    @Override
    public Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password) {
        return userAccountRepository.findUserAccountByEmailAndPassword(email, password);
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    //TODO CUSTOM METHOD REPO
    public void deleteUserAccount(UserAccount userAccount) {
        userAccountRepository.deleteById(userAccount.getEmail());
    }

    @Override
    public boolean emailUserAlreadyExist(String email) {
        Optional<UserAccount> userAccountAlreadyExist = userAccountRepository.findUserAccountByEmail(email);
        return userAccountAlreadyExist.isPresent();
    }
}