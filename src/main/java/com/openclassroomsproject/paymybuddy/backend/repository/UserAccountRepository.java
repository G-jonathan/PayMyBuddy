package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password);

    Optional<UserAccount> findUserAccountByEmail(String email);
}