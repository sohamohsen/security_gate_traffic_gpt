package com.research.service;

import com.research.exception.DuplicateIdException;
import com.research.model.Resident;
import com.research.repository.ResidentRepository;

import java.util.List;

public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ValidationService validationService;

    public ResidentService(ResidentRepository residentRepository,
                           ValidationService validationService) {
        this.residentRepository = residentRepository;
        this.validationService = validationService;
    }

    public void addResident(Resident resident) {
        validationService.validateId(resident.getId());
        validationService.validateRequiredString(resident.getFullName(), "Full Name");
        validationService.validateEmail(resident.getEmail());
        validationService.validatePhone(resident.getPhone());
        validationService.validateRequiredString(resident.getUnitNumber(), "Unit Number");

        if (residentRepository.findByEmail(resident.getEmail()).isPresent()) {
            throw new DuplicateIdException("Resident with this email already exists.");
        }

        residentRepository.add(resident);
    }

    public Resident getResidentById(int id) {
        return residentRepository.getById(id);
    }

    public List<Resident> getAllResidents() {
        return List.copyOf(residentRepository.getAll());
    }

    public void updateResident(Resident resident) {
        residentRepository.update(resident);
    }

    public void deleteResident(int id) {
        residentRepository.delete(id);
    }
}
