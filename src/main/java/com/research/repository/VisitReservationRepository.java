package com.research.repository;

import com.research.model.VisitReservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VisitReservationRepository extends BaseRepository<VisitReservation> {

    // ---------------- FIND BY VEHICLE PLATE ----------------
    public Optional<VisitReservation> findByVehiclePlate(String plateNumber) {
        return storage.values()
                .stream()
                .filter(reservation ->
                        reservation.getVehiclePlate()
                                .equalsIgnoreCase(plateNumber))
                .findFirst();
    }

    // ---------------- FIND BY DATE ----------------
    public List<VisitReservation> findByDate(LocalDate date) {
        return storage.values()
                .stream()
                .filter(reservation ->
                        reservation.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }
}
