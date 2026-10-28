package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {

    Equipement createEquipement(Equipement equipement);

    Equipement getEquipementById(Long idEquipement);

    Equipement updateEquipement(Equipement equipement);

    void deleteEquipement(Long idEquipement);

    List<Equipement> getAllEquipements();
}
