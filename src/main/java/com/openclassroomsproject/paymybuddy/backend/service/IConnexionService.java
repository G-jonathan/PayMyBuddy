package com.openclassroomsproject.paymybuddy.backend.service;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import java.util.List;

public interface IConnexionService {

    List<Connexion> findAllConnexion();
}