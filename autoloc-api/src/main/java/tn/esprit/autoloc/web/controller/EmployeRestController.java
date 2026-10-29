package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeRestController {

    private final IEmployeService employeService;

    // GET /api/employes
    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    // GET /api/employes/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Employe getEmployeById(@PathVariable("id") Long idEmploye) {
        return employeService.getEmployeById(idEmploye);
    }

    // POST /api/employes : @RequestBody convertit le JSON en Employe
    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.createEmploye(employe);
    }

    // PUT /api/employes/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Employe updateEmploye(@PathVariable("id") Long idEmploye, @RequestBody Employe employe) {
        employe.setIdEmploye(idEmploye);
        return employeService.updateEmploye(employe);
    }

    // DELETE /api/employes/{id}
    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable("id") Long idEmploye) {
        employeService.deleteEmploye(idEmploye);
    }

    // GET /api/employes/role/{role} : @PathVariable sur une enum
    @GetMapping("/role/{role}")
    public List<Employe> getEmployesParRole(@PathVariable RoleEmploye role) {
        return employeService.getAllEmployes().stream()
                .filter(emp -> emp.getRole() == role)
                .toList();
    }
}
