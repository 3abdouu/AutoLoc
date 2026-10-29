package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementRestController {

    private final IPaiementService paiementService;

    // GET /api/paiements
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    // GET /api/paiements/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable("id") Long idPaiement) {
        return paiementService.getPaiementById(idPaiement);
    }

    // POST /api/paiements : @RequestBody convertit le JSON en Paiement
    @PostMapping
    public Paiement createPaiement(@RequestBody Paiement paiement) {
        return paiementService.createPaiement(paiement);
    }

    // PUT /api/paiements/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable("id") Long idPaiement, @RequestBody Paiement paiement) {
        paiement.setIdPaiement(idPaiement);
        return paiementService.updatePaiement(paiement);
    }

    // DELETE /api/paiements/{id}
    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable("id") Long idPaiement) {
        paiementService.deletePaiement(idPaiement);
    }

    // GET /api/paiements/mode/{mode} : @PathVariable sur une enum
    @GetMapping("/mode/{mode}")
    public List<Paiement> getPaiementsParMode(@PathVariable ModePaiement mode) {
        return paiementService.getAllPaiements().stream()
                .filter(p -> p.getModePaiement() == mode)
                .toList();
    }
}
