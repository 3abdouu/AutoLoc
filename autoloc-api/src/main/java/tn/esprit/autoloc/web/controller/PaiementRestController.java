package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Paiement", description = "Gestion des paiements")
@RestController
@RequestMapping("paiements")
@RequiredArgsConstructor
public class PaiementRestController {

    private final IPaiementService paiementService;

    // GET /api/paiements
    @Operation(summary = "Lister tous les paiements")
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    // GET /api/paiements/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher un paiement par identifiant")
    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable("id") Long idPaiement) {
        return paiementService.getPaiementById(idPaiement);
    }

    // POST /api/paiements : @RequestBody convertit le JSON en Paiement
    @Operation(summary = "Ajouter un paiement")
    @PostMapping
    public Paiement createPaiement(@RequestBody Paiement paiement) {
        return paiementService.createPaiement(paiement);
    }

    // PUT /api/paiements/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier un paiement")
    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable("id") Long idPaiement, @RequestBody Paiement paiement) {
        paiement.setIdPaiement(idPaiement);
        return paiementService.updatePaiement(paiement);
    }

    // DELETE /api/paiements/{id}
    @Operation(summary = "Supprimer un paiement")
    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable("id") Long idPaiement) {
        paiementService.deletePaiement(idPaiement);
    }

    // GET /api/paiements/mode/{mode} : @PathVariable sur une enum
    @Operation(summary = "Lister les paiements par mode de paiement")
    @GetMapping("/mode/{mode}")
    public List<Paiement> getPaiementsParMode(@PathVariable ModePaiement mode) {
        return paiementService.getAllPaiements().stream()
                .filter(p -> p.getModePaiement() == mode)
                .toList();
    }
}
