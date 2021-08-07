package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import com.openclassroomsproject.paymybuddy.backend.repository.ConnexionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConnexionServiceImpl implements IConnexionService {
    private final ConnexionRepository connexionRepository;

    public ConnexionServiceImpl(ConnexionRepository connexionRepository) {
        this.connexionRepository = connexionRepository;
    }

    @Override
    public List<Connexion> findAllConnexion() {
        return connexionRepository.findAll();
    }
}