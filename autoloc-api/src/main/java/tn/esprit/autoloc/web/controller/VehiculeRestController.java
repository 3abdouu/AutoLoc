package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    // GET /api/vehicules
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    // GET /api/vehicules/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Vehicule getVehiculeById(@PathVariable("id") Long idVehicule) {
        return vehiculeService.getVehiculeById(idVehicule);
    }

    // POST /api/vehicules : @RequestBody convertit le JSON en Vehicule
    @PostMapping
    public Vehicule createVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.createVehicule(vehicule);
    }

    // PUT /api/vehicules/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Vehicule updateVehicule(@PathVariable("id") Long idVehicule, @RequestBody Vehicule vehicule) {
        vehicule.setIdVehicule(idVehicule);
        return vehiculeService.updateVehicule(vehicule);
    }

    // DELETE /api/vehicules/{id}
    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable("id") Long idVehicule) {
        vehiculeService.deleteVehicule(idVehicule);
    }

    // GET /api/vehicules/recherche?marque=Peugeot&statut=DISPONIBLE : @RequestParam lit la query string
    @GetMapping("/recherche")
    public List<Vehicule> rechercherVehicules(@RequestParam(required = false) String marque,
            @RequestParam(required = false) StatutVehicule statut) {
        return vehiculeService.getAllVehicules().stream()
                .filter(v -> (marque == null || v.getMarque().equalsIgnoreCase(marque))
                        && (statut == null || v.getStatut() == statut))
                .toList();
    }

    // GET /api/vehicules/categorie/{categorie} : @PathVariable sur une enum
    @GetMapping("/categorie/{categorie}")
    public List<Vehicule> getVehiculesByCategorie(@PathVariable CategorieVehicule categorie) {
        return vehiculeService.getAllVehicules().stream()
                .filter(v -> v.getCategorie() == categorie)
                .toList();
    }

    // PATCH /api/vehicules/{id}/statut?statut=MAINTENANCE : modification partielle
    @PatchMapping("/{id}/statut")
    public Vehicule changerStatut(@PathVariable("id") Long idVehicule, @RequestParam StatutVehicule statut) {
        Vehicule vehicule = vehiculeService.getVehiculeById(idVehicule);
        vehicule.setStatut(statut);
        return vehiculeService.updateVehicule(vehicule);
    }

    // PATCH /api/vehicules/{id}/tarif?valeur=120.00 : modification partielle
    @PatchMapping("/{id}/tarif")
    public Vehicule changerTarif(@PathVariable("id") Long idVehicule, @RequestParam("valeur") BigDecimal tarifJournalier) {
        Vehicule vehicule = vehiculeService.getVehiculeById(idVehicule);
        vehicule.setTarifJournalier(tarifJournalier);
        return vehiculeService.updateVehicule(vehicule);
    }

    // GET /api/vehicules/info : @RequestHeader lit un en-tête HTTP
    @GetMapping("/info")
    public String info(@RequestHeader(value = "User-Agent", required = false) String userAgent) {
        return "Nombre de véhicules : " + vehiculeService.getAllVehicules().size() + " (client : " + userAgent + ")";
    }
}
