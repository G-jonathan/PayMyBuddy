package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import com.openclassroomsproject.paymybuddy.backend.repository.ConnexionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnexionServiceImpl implements IConnexionService {
    private final SecurityProvider securityProvider;
    private final ConnexionRepository connexionRepository;

    public ConnexionServiceImpl(SecurityProvider securityProvider, ConnexionRepository connexionRepository) {
        this.securityProvider = securityProvider;
        this.connexionRepository = connexionRepository;
    }

    @Override
    public void addConnection(Connexion connexion) {
        connexionRepository.save(connexion);
    }

    @Override
    public String findConnexionEmailById(int id) {
        Connexion connexion = connexionRepository.findConnexionById(id);
        return connexion.getConnexionEmail();
    }

    @Override
    public List<String> findAllConnexionByUserAccountEmail() {
        String email = securityProvider.getAuthenticatedUser().getUsername();
        List<Connexion> connexionList = connexionRepository.findAllConnexionByUserAccountEmail(email);
        List<String> emailConnectionList = new ArrayList<>();
        for (Connexion connexion : connexionList) {
            emailConnectionList.add(connexion.getConnexionEmail());
        }
        return emailConnectionList;
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