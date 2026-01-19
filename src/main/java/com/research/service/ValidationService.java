package com.research.service;

import com.research.exception.BusinessRuleViolationException;

public class ValidationService {

    // ---------------- BASIC STRING VALIDATION ----------------
    public void validateRequiredString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new BusinessRuleViolationException(
                    fieldName + " must not be empty."
            );
        }
    }

    // ---------------- EMAIL VALIDATION ----------------
    public void validateEmail(String email) {
        validateRequiredString(email, "Email");

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new BusinessRuleViolationException(
                    "Invalid email format."
            );
        }
    }

    // ---------------- PHONE VALIDATION ----------------
    public void validatePhone(String phone) {
        validateRequiredString(phone, "Phone number");

        if (!phone.matches("^\\+?[0-9]{10,15}$")) {
            throw new BusinessRuleViolationException(
                    "Invalid phone number format."
            );
        }
    }

    // ---------------- POSITIVE ID VALIDATION ----------------
    public void validateId(int id) {
        if (id <= 0) {
            throw new BusinessRuleViolationException(
                    "ID must be a positive number."
            );
        }
    }

    // ---------------- POSITIVE NUMBER VALIDATION ----------------
    public void validatePositiveNumber(int value, String fieldName) {
        if (value <= 0) {
            throw new BusinessRuleViolationException(
                    fieldName + " must be greater than zero."
            );
        }
    }

    // ---------------- OBJECT NOT NULL ----------------
    public void validateNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new BusinessRuleViolationException(
                    fieldName + " must not be null."
            );
        }
    }
}
