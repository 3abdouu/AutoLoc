package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence createAgence(Agence agence);

    Agence getAgenceById(Long idAgence);

    Agence updateAgence(Agence agence);

    void deleteAgence(Long idAgence);

    List<Agence> getAllAgences();
}
