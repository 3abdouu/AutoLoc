package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    // GET /api/maintenances
    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    // GET /api/maintenances/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable("id") Long idMaintenance) {
        return maintenanceService.getMaintenanceById(idMaintenance);
    }

    // POST /api/maintenances : @RequestBody convertit le JSON en Maintenance
    @PostMapping
    public Maintenance createMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.createMaintenance(maintenance);
    }

    // PUT /api/maintenances/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Maintenance updateMaintenance(@PathVariable("id") Long idMaintenance, @RequestBody Maintenance maintenance) {
        maintenance.setIdMaintenance(idMaintenance);
        return maintenanceService.updateMaintenance(maintenance);
    }

    // DELETE /api/maintenances/{id}
    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable("id") Long idMaintenance) {
        maintenanceService.deleteMaintenance(idMaintenance);
    }

    // GET /api/maintenances/en-cours : maintenances sans date de fin
    @GetMapping("/en-cours")
    public List<Maintenance> getMaintenancesEnCours() {
        return maintenanceService.getAllMaintenances().stream()
                .filter(m -> m.getDateFin() == null)
                .toList();
    }

    // PATCH /api/maintenances/{id}/cloturer?dateFin=2026-10-30 : modification partielle
    @PatchMapping("/{id}/cloturer")
    public Maintenance cloturerMaintenance(@PathVariable("id") Long idMaintenance,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(idMaintenance);
        maintenance.setDateFin(dateFin != null ? dateFin : LocalDate.now());
        return maintenanceService.updateMaintenance(maintenance);
    }
}
