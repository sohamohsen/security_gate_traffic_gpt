package com.research;

import com.research.repository.*;
import com.research.service.*;
import com.research.ui.MainMenu;

public class Main {


    public static void main(String[] args) {

        // ---------------- REPOSITORIES ----------------
        ResidentRepository residentRepository = new ResidentRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        GateLaneRepository gateLaneRepository = new GateLaneRepository();
        GatePassRepository gatePassRepository = new GatePassRepository();
        VisitReservationRepository visitReservationRepository = new VisitReservationRepository();

        // ---------------- SHARED SERVICES ----------------
        ValidationService validationService = new ValidationService();

        // ---------------- BUSINESS SERVICES ----------------
        ResidentService residentService =
                new ResidentService(residentRepository, validationService);

        VehicleService vehicleService =
                new VehicleService(vehicleRepository, validationService);

        GateLaneService gateLaneService =
                new GateLaneService(gateLaneRepository, validationService);

        VisitReservationService visitReservationService =
                new VisitReservationService(visitReservationRepository, validationService);

        GatePassService gatePassService =
                new GatePassService(gatePassRepository, gateLaneRepository);

        // ---------------- START UI ----------------
        MainMenu mainMenu = new MainMenu(
                residentService,
                vehicleService,
                gateLaneService,
                gatePassService,
                visitReservationService
        );

        mainMenu.start();
    }
}
