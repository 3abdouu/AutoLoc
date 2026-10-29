package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Client", description = "Gestion des clients")
@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientService clientService;

    // GET /api/clients
    @Operation(summary = "Lister tous les clients")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // GET /api/clients/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher un client par identifiant")
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Long idClient) {
        return clientService.getClientById(idClient);
    }

    // POST /api/clients : @RequestBody convertit le JSON en Client
    @Operation(summary = "Ajouter un client")
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // PUT /api/clients/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier un client")
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable("id") Long idClient, @RequestBody Client client) {
        client.setIdClient(idClient);
        return clientService.updateClient(client);
    }

    // DELETE /api/clients/{id}
    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") Long idClient) {
        clientService.deleteClient(idClient);
    }

    // GET /api/clients/recherche?nom=Ben : @RequestParam lit la query string
    @Operation(summary = "Rechercher des clients par nom")
    @GetMapping("/recherche")
    public List<Client> rechercherParNom(@RequestParam String nom) {
        return clientService.getAllClients().stream()
                .filter(c -> c.getNom().toLowerCase().contains(nom.toLowerCase()))
                .toList();
    }
}
