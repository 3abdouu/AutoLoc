package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Employe", description = "Gestion des employés")
@RestController
@RequestMapping("employes")
@RequiredArgsConstructor
public class EmployeRestController {

    private final IEmployeService employeService;

    // GET /api/employes
    @Operation(summary = "Lister tous les employés")
    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    // GET /api/employes/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher un employé par identifiant")
    @GetMapping("/{id}")
    public Employe getEmployeById(@PathVariable("id") Long idEmploye) {
        return employeService.getEmployeById(idEmploye);
    }

    // POST /api/employes : @RequestBody convertit le JSON en Employe
    @Operation(summary = "Ajouter un employé")
    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.createEmploye(employe);
    }

    // PUT /api/employes/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier un employé")
    @PutMapping("/{id}")
    public Employe updateEmploye(@PathVariable("id") Long idEmploye, @RequestBody Employe employe) {
        employe.setIdEmploye(idEmploye);
        return employeService.updateEmploye(employe);
    }

    // DELETE /api/employes/{id}
    @Operation(summary = "Supprimer un employé")
    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable("id") Long idEmploye) {
        employeService.deleteEmploye(idEmploye);
    }

    // GET /api/employes/role/{role} : @PathVariable sur une enum
    @Operation(summary = "Lister les employés par rôle")
    @GetMapping("/role/{role}")
    public List<Employe> getEmployesParRole(@PathVariable RoleEmploye role) {
        return employeService.getAllEmployes().stream()
                .filter(emp -> emp.getRole() == role)
                .toList();
    }
}
