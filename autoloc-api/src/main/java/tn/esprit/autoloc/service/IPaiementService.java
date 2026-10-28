package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    Paiement createPaiement(Paiement paiement);

    Paiement getPaiementById(Long idPaiement);

    Paiement updatePaiement(Paiement paiement);

    void deletePaiement(Long idPaiement);

    List<Paiement> getAllPaiements();
}
