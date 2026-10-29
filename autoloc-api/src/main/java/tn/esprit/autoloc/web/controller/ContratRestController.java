package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Contrat", description = "Gestion des contrats")
@RestController
@RequestMapping("contrats")
@RequiredArgsConstructor
public class ContratRestController {

    private final IContratService contratService;

    // GET /api/contrats
    @Operation(summary = "Lister tous les contrats")
    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    // GET /api/contrats/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher un contrat par identifiant")
    @GetMapping("/{id}")
    public Contrat getContratById(@PathVariable("id") Long idContrat) {
        return contratService.getContratById(idContrat);
    }

    // POST /api/contrats : @RequestBody convertit le JSON en Contrat
    @Operation(summary = "Ajouter un contrat")
    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        return contratService.createContrat(contrat);
    }

    // PUT /api/contrats/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier un contrat")
    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable("id") Long idContrat, @RequestBody Contrat contrat) {
        contrat.setIdContrat(idContrat);
        return contratService.updateContrat(contrat);
    }

    // DELETE /api/contrats/{id}
    @Operation(summary = "Supprimer un contrat")
    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable("id") Long idContrat) {
        contratService.deleteContrat(idContrat);
    }

    // GET /api/contrats/filtre?valide=false : @RequestParam avec valeur par défaut
    @Operation(summary = "Filtrer les contrats par validité")
    @GetMapping("/filtre")
    public List<Contrat> getContratsParValidite(@RequestParam(defaultValue = "true") boolean valide) {
        return contratService.getAllContrats().stream()
                .filter(c -> c.isValide() == valide)
                .toList();
    }

    // PATCH /api/contrats/{id}/valider : modification partielle
    @Operation(summary = "Valider un contrat")
    @PatchMapping("/{id}/valider")
    public Contrat validerContrat(@PathVariable("id") Long idContrat) {
        Contrat contrat = contratService.getContratById(idContrat);
        contrat.setValide(true);
        return contratService.updateContrat(contrat);
    }
}
