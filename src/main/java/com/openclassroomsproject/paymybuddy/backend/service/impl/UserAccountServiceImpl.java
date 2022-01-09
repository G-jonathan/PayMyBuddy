package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.repository.UserAccountRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
    private final UserAccountRepository userAccountRepository;
    private SecurityProvider securityProvider;

    public UserAccountServiceImpl(SecurityProvider securityProvider, UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
        this.securityProvider = securityProvider;
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
    public double getAccountBalance() {
        UserAccount userAccount = new UserAccount();
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<UserAccount> OptionalUserAccount = userAccountRepository.findUserAccountByEmail(userAccountEmail);
        if (OptionalUserAccount.isPresent()) {
            userAccount = OptionalUserAccount.get();
        }
        return userAccount.getBalance();
    }

    @Override
    public boolean updateUsersAccountsBeforeSavingTheTransaction(VisibleBuddyTransaction visiBleBuddyTransaction, boolean isARollback) {
        try {
            double transmitterAmountAfterModification;
            double beneficiaryAmountAfterModification;
            double amount = visiBleBuddyTransaction.getAmount();
            String transmitterEmail = securityProvider.getAuthenticatedUser().getUsername();
            String beneficiaryEmail = visiBleBuddyTransaction.getConnexionEmail();
            Optional<UserAccount> transmitterAccount = userAccountRepository.findUserAccountByEmail(transmitterEmail);
            Optional<UserAccount> beneficiaryAccount = userAccountRepository.findUserAccountByEmail(beneficiaryEmail);
            if (transmitterAccount.isPresent()) {
                UserAccount transmitterAccountUpdated = transmitterAccount.get();
                double transmitterAmountBeforeModification = transmitterAccountUpdated.getBalance();
                if (isARollback){
                    transmitterAmountAfterModification = transmitterAmountBeforeModification + amount;
                } else {
                    transmitterAmountAfterModification = transmitterAmountBeforeModification - amount;
                }
                transmitterAccountUpdated.setBalance(transmitterAmountAfterModification);
                userAccountRepository.save(transmitterAccountUpdated);
            }
            if (beneficiaryAccount.isPresent()) {
                UserAccount beneficiaryAccountUpdated = beneficiaryAccount.get();
                double beneficiaryAmountBeforeModification = beneficiaryAccountUpdated.getBalance();
                if (!isARollback) {
                    beneficiaryAmountAfterModification = beneficiaryAmountBeforeModification - amount;
                } else {
                    beneficiaryAmountAfterModification = beneficiaryAmountBeforeModification + amount;
                }
                beneficiaryAccountUpdated.setBalance(beneficiaryAmountAfterModification);
                userAccountRepository.save(beneficiaryAccountUpdated);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
    public boolean isUserBalanceSufficient(int amountRequestedInt) {
        String userAccountEmail = securityProvider.getAuthenticatedUser().getUsername();
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findUserAccountByEmail(userAccountEmail);
        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
            double amountAvailable = userAccount.getBalance();
            return amountAvailable > (double) amountRequestedInt || amountAvailable == (double) amountRequestedInt;
        }
        return false;
    }
}