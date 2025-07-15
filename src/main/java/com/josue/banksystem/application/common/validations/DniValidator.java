package com.josue.banksystem.application.common.validations;

import com.josue.banksystem.application.common.annotations.ValidDni;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<ValidDni, String> {

    @Override
    public boolean isValid(String dni, ConstraintValidatorContext constraintValidatorContext) {
        // If null or not exactly 10 digits, it's invalid
        if (dni == null || !dni.matches("\\d{10}")) {
            return false;
        }

        // Coefficients for the first 9 digits (Module 10 algorithm)
        int[] coef = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int sum = 0;

        // Apply alternating weights and subtract 9 if product > 9
        for (int i = 0; i < 9; i++) {
            int val = Character.getNumericValue(dni.charAt(i)) * coef[i];
            if (val > 9) {
                val -= 9;
            }
            sum += val;
        }

        // Compute the check digit (if sum % 10 == 0 → 0, else 10 - (sum % 10))
        int checkDigit = (sum % 10 == 0) ? 0 : 10 - (sum % 10);

        // Extract the actual last digit from the cedula
        int actualDigit = Character.getNumericValue(dni.charAt(9));

        // Validate province code (first two digits must be 1–24)
        int province = Integer.parseInt(dni.substring(0, 2));
        if (province < 1 || province > 24) {
            return false;
        }

        // Return whether calculated check digit matches the last digit
        return checkDigit == actualDigit;
    }
}
