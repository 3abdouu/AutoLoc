package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {

    Vehicule createVehicule(Vehicule vehicule);

    Vehicule getVehiculeById(Long idVehicule);

    Vehicule updateVehicule(Vehicule vehicule);

    void deleteVehicule(Long idVehicule);

    List<Vehicule> getAllVehicules();
}
