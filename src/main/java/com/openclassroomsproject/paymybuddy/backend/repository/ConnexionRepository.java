package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ConnexionRepository extends JpaRepository<Connexion, Integer> {

    List<Connexion> findAllConnexionByUserAccountEmail(String userAccountEmail);

    Connexion findConnexionById(int id);

    Connexion findConnexionByUserAccountEmailAndConnexionEmail(String userAccountEmail, String connexionEmail);

    Optional<Connexion>findAConnexionByUserAccountEmailAndConnexionEmail(String userAccountEmail, String connexionEmail);
}