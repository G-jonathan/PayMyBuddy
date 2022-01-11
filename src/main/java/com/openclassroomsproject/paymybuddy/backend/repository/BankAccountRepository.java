package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findAllBankAccountByUserAccountEmail(String userAccountEmail);

    Optional<BankAccount> findBankAccountByUserAccountEmail(String UserAccountEmail);

    @Transactional
    void deleteBankAccountByUserAccountEmail(String UserAccountEmail);
}