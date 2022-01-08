package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.Connexion;
import com.openclassroomsproject.paymybuddy.backend.repository.ConnexionRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnexionServiceImpl implements IConnexionService {
    private final SecurityProvider securityProvider;
    private final ConnexionRepository connexionRepository;

    public ConnexionServiceImpl(SecurityProvider securityProvider, ConnexionRepository connexionRepository) {
        this.securityProvider = securityProvider;
        this.connexionRepository = connexionRepository;
    }

    @Override
    public void addConnection(String emailConnexion) {
        Connexion connexion = new Connexion();
        String email = securityProvider.getAuthenticatedUser().getUsername();
        connexion.setConnexionEmail(emailConnexion);
        connexion.setUserAccountEmail(email);
        connexionRepository.save(connexion);
    }

    @Override
    public String findConnexionEmailById(int id) {
        Connexion connexion = connexionRepository.findConnexionById(id);
        return connexion.getConnexionEmail();
    }

    @Override
    public int findConnexionIdByUserAccountEmailAndConnexionEmail(String userAccountEmail, String connexionEmail) {
        Connexion connection = connexionRepository.findConnexionByUserAccountEmailAndConnexionEmail(userAccountEmail, connexionEmail);
        return connection.getId();
    }

    @Override
    public boolean findConnexionByUserAccountEmailAndConnexionEmail(String connexionEmail) {
        String email = securityProvider.getAuthenticatedUser().getUsername();
        Optional<Connexion> connection = connexionRepository.findAConnexionByUserAccountEmailAndConnexionEmail(email, connexionEmail);
        return connection.isPresent();
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