package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Vehicule", description = "Gestion des véhicules")
@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    // GET /api/vehicules
    @Operation(summary = "Lister tous les véhicules")
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    // GET /api/vehicules/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher un véhicule par identifiant")
    @GetMapping("/{id}")
    public Vehicule getVehiculeById(@PathVariable("id") Long idVehicule) {
        return vehiculeService.getVehiculeById(idVehicule);
    }

    // POST /api/vehicules : @RequestBody convertit le JSON en Vehicule
    @Operation(summary = "Ajouter un véhicule")
    @PostMapping
    public Vehicule createVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.createVehicule(vehicule);
    }

    // PUT /api/vehicules/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier un véhicule")
    @PutMapping("/{id}")
    public Vehicule updateVehicule(@PathVariable("id") Long idVehicule, @RequestBody Vehicule vehicule) {
        vehicule.setIdVehicule(idVehicule);
        return vehiculeService.updateVehicule(vehicule);
    }

    // DELETE /api/vehicules/{id}
    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable("id") Long idVehicule) {
        vehiculeService.deleteVehicule(idVehicule);
    }

    // GET /api/vehicules/recherche?marque=Peugeot&statut=DISPONIBLE : @RequestParam lit la query string
    @Operation(summary = "Rechercher des véhicules par marque et/ou statut")
    @GetMapping("/recherche")
    public List<Vehicule> rechercherVehicules(@RequestParam(required = false) String marque,
            @RequestParam(required = false) StatutVehicule statut) {
        return vehiculeService.getAllVehicules().stream()
                .filter(v -> (marque == null || v.getMarque().equalsIgnoreCase(marque))
                        && (statut == null || v.getStatut() == statut))
                .toList();
    }

    // GET /api/vehicules/categorie/{categorie} : @PathVariable sur une enum
    @Operation(summary = "Lister les véhicules d'une catégorie")
    @GetMapping("/categorie/{categorie}")
    public List<Vehicule> getVehiculesByCategorie(@PathVariable CategorieVehicule categorie) {
        return vehiculeService.getAllVehicules().stream()
                .filter(v -> v.getCategorie() == categorie)
                .toList();
    }

    // PATCH /api/vehicules/{id}/statut?statut=MAINTENANCE : modification partielle
    @Operation(summary = "Changer le statut d'un véhicule")
    @PatchMapping("/{id}/statut")
    public Vehicule changerStatut(@PathVariable("id") Long idVehicule, @RequestParam StatutVehicule statut) {
        Vehicule vehicule = vehiculeService.getVehiculeById(idVehicule);
        vehicule.setStatut(statut);
        return vehiculeService.updateVehicule(vehicule);
    }

    // PATCH /api/vehicules/{id}/tarif?valeur=120.00 : modification partielle
    @Operation(summary = "Modifier le tarif journalier d'un véhicule")
    @PatchMapping("/{id}/tarif")
    public Vehicule changerTarif(@PathVariable("id") Long idVehicule, @RequestParam("valeur") BigDecimal tarifJournalier) {
        Vehicule vehicule = vehiculeService.getVehiculeById(idVehicule);
        vehicule.setTarifJournalier(tarifJournalier);
        return vehiculeService.updateVehicule(vehicule);
    }

    // GET /api/vehicules/info : @RequestHeader lit un en-tête HTTP
    @Operation(summary = "Afficher le nombre de véhicules")
    @GetMapping("/info")
    public String info(@RequestHeader(value = "User-Agent", required = false) String userAgent) {
        return "Nombre de véhicules : " + vehiculeService.getAllVehicules().size() + " (client : " + userAgent + ")";
    }
}
