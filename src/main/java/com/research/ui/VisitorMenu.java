package com.research.ui;

import com.research.model.VisitReservation;
import com.research.service.VisitReservationService;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.research.util.InputUtil.readInt;
import static com.research.util.InputUtil.readString;

public class VisitorMenu {

    private final VisitReservationService reservationService;

    public VisitorMenu(VisitReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Visitor Management ---");
            System.out.println("1. Create Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Cancel Reservation");
            System.out.println("0. Back");

            try {
                switch (readInt("Choose: ")) {
                    case 1 -> reservationService.createReservation(new VisitReservation(
                            readInt("ID: "),
                            readString("Visitor Name: "),
                            readString("Vehicle Plate: "),
                            LocalDate.parse(readString("Visit Date (YYYY-MM-DD): ")),
                            LocalTime.parse(readString("Visit Time (HH:MM): ")),
                            readInt("Passengers: ")
                    ));
                    case 2 -> reservationService.getAllReservations()
                            .forEach(r -> System.out.println(
                                    r.getId() + " | " + r.getVehiclePlate()));
                    case 3 -> reservationService.cancelReservation(readInt("Reservation ID: "));
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
