package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceRestController {

    private final IAgenceService agenceService;

    // GET /api/agences
    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.getAllAgences();
    }

    // GET /api/agences/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Agence getAgenceById(@PathVariable("id") Long idAgence) {
        return agenceService.getAgenceById(idAgence);
    }

    // POST /api/agences : @RequestBody convertit le JSON en Agence
    @PostMapping
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceService.createAgence(agence);
    }

    // PUT /api/agences/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Agence updateAgence(@PathVariable("id") Long idAgence, @RequestBody Agence agence) {
        agence.setIdAgence(idAgence);
        return agenceService.updateAgence(agence);
    }

    // DELETE /api/agences/{id}
    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable("id") Long idAgence) {
        agenceService.deleteAgence(idAgence);
    }

    // GET /api/agences/recherche?ville=Tunis : @RequestParam lit la query string
    @GetMapping("/recherche")
    public List<Agence> rechercherParVille(@RequestParam String ville) {
        return agenceService.getAllAgences().stream()
                .filter(a -> a.getVille().equalsIgnoreCase(ville))
                .toList();
    }
}
