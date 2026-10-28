package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe createEmploye(Employe employe);

    Employe getEmployeById(Long idEmploye);

    Employe updateEmploye(Employe employe);

    void deleteEmploye(Long idEmploye);

    List<Employe> getAllEmployes();
}
