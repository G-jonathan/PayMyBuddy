package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.repository.UserAccountRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IAuthoritiesService;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final IAuthoritiesService authoritiesService;
    private SecurityProvider securityProvider;

    public UserAccountServiceImpl(SecurityProvider securityProvider, UserAccountRepository userAccountRepository, IAuthoritiesService authoritiesService) {
        this.userAccountRepository = userAccountRepository;
        this.securityProvider = securityProvider;
        this.authoritiesService = authoritiesService;
    }

    @Override
    public void adminAccountProvision(double amount) {
        UserAccount adminAccount = findUserAccountByEmail("admin@admin.com");
        adminAccount.setBalance(adminAccount.getBalance() +amount);
        userAccountRepository.save(adminAccount);
    }

    @Override
    public boolean createUserAccount(UserAccount userAccount) {
        try {
            String passwordNotEncrypted = userAccount.getPassword();
            userAccount.setPassword(new BCryptPasswordEncoder().encode(passwordNotEncrypted));
            userAccount.setActivated(true);
            userAccountRepository.save(userAccount);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password) {
        return userAccountRepository.findUserAccountByEmailAndPassword(email, password);
    }

    @Override
    public UserAccount findUserAccountByEmail(String email) {
        UserAccount userAccount = new UserAccount();
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findUserAccountByEmail(email);
        if (optionalUserAccount.isPresent()) {
            userAccount = optionalUserAccount.get();
        }
        return userAccount;
    }

    @Override
    public double getAccountBalance() {
        UserAccount userAccount = new UserAccount();
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findUserAccountByEmail(userAccountEmail);
        if (optionalUserAccount.isPresent()) {
            userAccount = optionalUserAccount.get();
        }
        return userAccount.getBalance();
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) {
        userAccountRepository.deleteById(userAccount.getEmail());
    }

    @Override
    public boolean emailUserAlreadyExist(String email) {
        Optional<UserAccount> userAccountAlreadyExist = userAccountRepository.findUserAccountByEmail(email);
        return userAccountAlreadyExist.isPresent();
    }

    @Override
    public boolean isUserBalanceSufficient(double amountRequested) {
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findUserAccountByEmail(userAccountEmail);
        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
            double amountAvailable = userAccount.getBalance();
            return amountAvailable > amountRequested || amountAvailable == amountRequested;
        }
        return false;
    }
}