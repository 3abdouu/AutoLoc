package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationRestController {

    private final IReservationService reservationService;

    // GET /api/reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // GET /api/reservations/{id} : @PathVariable lit l'id dans l'URL
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable("id") Long idReservation) {
        return reservationService.getReservationById(idReservation);
    }

    // POST /api/reservations : @RequestBody convertit le JSON en Reservation
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // PUT /api/reservations/{id} : @PathVariable + @RequestBody
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") Long idReservation, @RequestBody Reservation reservation) {
        reservation.setIdReservation(idReservation);
        return reservationService.updateReservation(reservation);
    }

    // DELETE /api/reservations/{id}
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long idReservation) {
        reservationService.deleteReservation(idReservation);
    }

    // GET /api/reservations/statut/{statut} : @PathVariable sur une enum
    @GetMapping("/statut/{statut}")
    public List<Reservation> getReservationsParStatut(@PathVariable StatutReservation statut) {
        return reservationService.getAllReservations().stream()
                .filter(r -> r.getStatut() == statut)
                .toList();
    }

    // PATCH /api/reservations/{id}/statut?statut=CONFIRMEE : modification partielle
    @PatchMapping("/{id}/statut")
    public Reservation changerStatut(@PathVariable("id") Long idReservation, @RequestParam StatutReservation statut) {
        Reservation reservation = reservationService.getReservationById(idReservation);
        reservation.setStatut(statut);
        return reservationService.updateReservation(reservation);
    }
}
