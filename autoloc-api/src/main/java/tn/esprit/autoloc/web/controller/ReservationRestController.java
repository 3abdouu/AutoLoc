package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Tag(name = "Reservation", description = "Gestion des réservations")
@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationRestController {

    private final IReservationService reservationService;

    // GET /api/reservations
    @Operation(summary = "Lister toutes les réservations")
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // GET /api/reservations/{id} : @PathVariable lit l'id dans l'URL
    @Operation(summary = "Rechercher une réservation par identifiant")
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable("id") Long idReservation) {
        return reservationService.getReservationById(idReservation);
    }

    // POST /api/reservations : @RequestBody convertit le JSON en Reservation
    @Operation(summary = "Ajouter une réservation")
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // PUT /api/reservations/{id} : @PathVariable + @RequestBody
    @Operation(summary = "Modifier une réservation")
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") Long idReservation, @RequestBody Reservation reservation) {
        reservation.setIdReservation(idReservation);
        return reservationService.updateReservation(reservation);
    }

    // DELETE /api/reservations/{id}
    @Operation(summary = "Supprimer une réservation")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long idReservation) {
        reservationService.deleteReservation(idReservation);
    }

    // GET /api/reservations/statut/{statut} : @PathVariable sur une enum
    @Operation(summary = "Lister les réservations par statut")
    @GetMapping("/statut/{statut}")
    public List<Reservation> getReservationsParStatut(@PathVariable StatutReservation statut) {
        return reservationService.getAllReservations().stream()
                .filter(r -> r.getStatut() == statut)
                .toList();
    }

    // PATCH /api/reservations/{id}/statut?statut=CONFIRMEE : modification partielle
    @Operation(summary = "Changer le statut d'une réservation")
    @PatchMapping("/{id}/statut")
    public Reservation changerStatut(@PathVariable("id") Long idReservation, @RequestParam StatutReservation statut) {
        Reservation reservation = reservationService.getReservationById(idReservation);
        reservation.setStatut(statut);
        return reservationService.updateReservation(reservation);
    }
}
