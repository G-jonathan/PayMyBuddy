package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import java.util.List;

public interface IConnexionService {

    void addConnection(Connexion connexion);

    String findConnexionEmailById(int id);

    List<String> findAllConnexionByUserAccountEmail();

    void updateConnexion(Connexion connexion);

    void deleteConnexion(Connexion connexion);
}