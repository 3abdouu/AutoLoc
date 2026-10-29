package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Equipement", description = "Gestion des équipements")
@RestController
@RequestMapping("equipements")
@RequiredArgsConstructor
public class EquipementRestController {

    private final IEquipementService equipementService;

    // GET /api/equipements
    @Operation(summary = "Lister tous les équipements")
    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    // GET /api/equipements/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher un équipement par identifiant")
    @GetMapping("/{id}")
    public Equipement getEquipementById(@PathVariable("id") Long idEquipement) {
        return equipementService.getEquipementById(idEquipement);
    }

    // POST /api/equipements : @RequestBody convertit le JSON en Equipement
    @Operation(summary = "Ajouter un équipement")
    @PostMapping
    public Equipement createEquipement(@RequestBody Equipement equipement) {
        return equipementService.createEquipement(equipement);
    }

    // PUT /api/equipements/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier un équipement")
    @PutMapping("/{id}")
    public Equipement updateEquipement(@PathVariable("id") Long idEquipement, @RequestBody Equipement equipement) {
        equipement.setIdEquipement(idEquipement);
        return equipementService.updateEquipement(equipement);
    }

    // DELETE /api/equipements/{id}
    @Operation(summary = "Supprimer un équipement")
    @DeleteMapping("/{id}")
    public void deleteEquipement(@PathVariable("id") Long idEquipement) {
        equipementService.deleteEquipement(idEquipement);
    }

    // GET /api/equipements/recherche?libelle=GPS : @RequestParam lit la query string
    @Operation(summary = "Rechercher des équipements par libellé")
    @GetMapping("/recherche")
    public List<Equipement> rechercherParLibelle(@RequestParam String libelle) {
        return equipementService.getAllEquipements().stream()
                .filter(eq -> eq.getLibelle().toLowerCase().contains(libelle.toLowerCase()))
                .toList();
    }
}
