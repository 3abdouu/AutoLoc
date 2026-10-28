package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    Maintenance createMaintenance(Maintenance maintenance);

    Maintenance getMaintenanceById(Long idMaintenance);

    Maintenance updateMaintenance(Maintenance maintenance);

    void deleteMaintenance(Long idMaintenance);

    List<Maintenance> getAllMaintenances();
}
