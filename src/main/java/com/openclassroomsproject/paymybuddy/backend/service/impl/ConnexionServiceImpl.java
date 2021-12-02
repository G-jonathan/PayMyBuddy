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
    public void addConnection(Connexion connexion) {
        connexionRepository.save(connexion);
    }

    @Override
    public List<Connexion> findAllConnexionByUserAccountEmail(String userAccountEmail) {
        return connexionRepository.findAllConnexionByUserAccountEmail(userAccountEmail);
    }

    @Override
    public void updateConnexion(Connexion connexion) {
        connexionRepository.save(connexion);
    }

    @Override
    public void deleteConnexion(Connexion connexion) {
        connexionRepository.deleteById(connexion.getId());
    }
}