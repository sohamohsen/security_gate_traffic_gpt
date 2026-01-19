package com.research.service;

import com.research.model.VisitReservation;
import com.research.repository.VisitReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VisitReservationService {

    private final VisitReservationRepository reservationRepository;
    private final ValidationService validationService;

    public VisitReservationService(VisitReservationRepository reservationRepository,
                                   ValidationService validationService) {
        this.reservationRepository = reservationRepository;
        this.validationService = validationService;
    }

    public void createReservation(VisitReservation reservation) {
        validationService.validateId(reservation.getId());
        validationService.validateRequiredString(reservation.getVehiclePlate(), "Vehicle Plate");

        reservationRepository.add(reservation);
    }

    public boolean validateVisitorAccess(String plateNumber) {
        return reservationRepository.findByVehiclePlate(plateNumber)
                .filter(r -> r.getVisitDate().equals(LocalDate.now()))
                .filter(r -> !LocalTime.now().isBefore(r.getVisitTime()))
                .isPresent();
    }

    public List<VisitReservation> getAllReservations() {
        return List.copyOf(reservationRepository.getAll());
    }

    public void cancelReservation(int id) {
        reservationRepository.delete(id);
    }
}
