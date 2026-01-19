package com.research.repository;

import com.research.model.Resident;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResidentRepository extends BaseRepository<Resident> {

    // ---------------- FIND BY EMAIL ----------------
    public Optional<Resident> findByEmail(String email) {
        return storage.values()
                .stream()
                .filter(resident -> resident.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // ---------------- FIND BY UNIT NUMBER ----------------
    public Optional<Resident> findByUnitNumber(String unitNumber) {
        return storage.values()
                .stream()
                .filter(resident -> resident.getUnitNumber().equalsIgnoreCase(unitNumber))
                .findFirst();
    }

    // ---------------- SEARCH BY NAME ----------------
    public List<Resident> searchByName(String keyword) {
        return storage.values()
                .stream()
                .filter(resident ->
                        resident.getFullName().toLowerCase()
                                .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
