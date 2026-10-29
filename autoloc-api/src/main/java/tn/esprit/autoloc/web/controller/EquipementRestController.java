package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementRestController {

    private final IEquipementService equipementService;

    // GET /api/equipements
    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    // GET /api/equipements/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Equipement getEquipementById(@PathVariable("id") Long idEquipement) {
        return equipementService.getEquipementById(idEquipement);
    }

    // POST /api/equipements : @RequestBody convertit le JSON en Equipement
    @PostMapping
    public Equipement createEquipement(@RequestBody Equipement equipement) {
        return equipementService.createEquipement(equipement);
    }

    // PUT /api/equipements/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Equipement updateEquipement(@PathVariable("id") Long idEquipement, @RequestBody Equipement equipement) {
        equipement.setIdEquipement(idEquipement);
        return equipementService.updateEquipement(equipement);
    }

    // DELETE /api/equipements/{id}
    @DeleteMapping("/{id}")
    public void deleteEquipement(@PathVariable("id") Long idEquipement) {
        equipementService.deleteEquipement(idEquipement);
    }

    // GET /api/equipements/recherche?libelle=GPS : @RequestParam lit la query string
    @GetMapping("/recherche")
    public List<Equipement> rechercherParLibelle(@RequestParam String libelle) {
        return equipementService.getAllEquipements().stream()
                .filter(eq -> eq.getLibelle().toLowerCase().contains(libelle.toLowerCase()))
                .toList();
    }
}
