package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratRestController {

    private final IContratService contratService;

    // GET /api/contrats
    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    // GET /api/contrats/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Contrat getContratById(@PathVariable("id") Long idContrat) {
        return contratService.getContratById(idContrat);
    }

    // POST /api/contrats : @RequestBody convertit le JSON en Contrat
    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        return contratService.createContrat(contrat);
    }

    // PUT /api/contrats/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable("id") Long idContrat, @RequestBody Contrat contrat) {
        contrat.setIdContrat(idContrat);
        return contratService.updateContrat(contrat);
    }

    // DELETE /api/contrats/{id}
    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable("id") Long idContrat) {
        contratService.deleteContrat(idContrat);
    }

    // GET /api/contrats/filtre?valide=false : @RequestParam avec valeur par défaut
    @GetMapping("/filtre")
    public List<Contrat> getContratsParValidite(@RequestParam(defaultValue = "true") boolean valide) {
        return contratService.getAllContrats().stream()
                .filter(c -> c.isValide() == valide)
                .toList();
    }

    // PATCH /api/contrats/{id}/valider : modification partielle
    @PatchMapping("/{id}/valider")
    public Contrat validerContrat(@PathVariable("id") Long idContrat) {
        Contrat contrat = contratService.getContratById(idContrat);
        contrat.setValide(true);
        return contratService.updateContrat(contrat);
    }
}
