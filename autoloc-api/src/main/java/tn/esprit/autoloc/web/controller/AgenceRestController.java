package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Agence", description = "Gestion des agences")
@RestController
@RequestMapping("agences")
@RequiredArgsConstructor
public class AgenceRestController {

    private final IAgenceService agenceService;

    // GET /api/agences
    @Operation(summary = "Lister toutes les agences")
    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.getAllAgences();
    }

    // GET /api/agences/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher une agence par identifiant")
    @GetMapping("/{id}")
    public Agence getAgenceById(@PathVariable("id") Long idAgence) {
        return agenceService.getAgenceById(idAgence);
    }

    // POST /api/agences : @RequestBody convertit le JSON en Agence
    @Operation(summary = "Ajouter une agence")
    @PostMapping
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceService.createAgence(agence);
    }

    // PUT /api/agences/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier une agence")
    @PutMapping("/{id}")
    public Agence updateAgence(@PathVariable("id") Long idAgence, @RequestBody Agence agence) {
        agence.setIdAgence(idAgence);
        return agenceService.updateAgence(agence);
    }

    // DELETE /api/agences/{id}
    @Operation(summary = "Supprimer une agence")
    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable("id") Long idAgence) {
        agenceService.deleteAgence(idAgence);
    }

    // GET /api/agences/recherche?ville=Tunis : @RequestParam lit la query string
    @Operation(summary = "Rechercher des agences par ville")
    @GetMapping("/recherche")
    public List<Agence> rechercherParVille(@RequestParam String ville) {
        return agenceService.getAllAgences().stream()
                .filter(a -> a.getVille().equalsIgnoreCase(ville))
                .toList();
    }
}
