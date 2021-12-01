package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConnexionRepository extends JpaRepository<Connexion, Integer> {

    List<Connexion> findAllConnexionByUserAccountEmail(String userAccountEmail);
}