package co.parameta.employee.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validator for date format validation.
 * Validates that a date string matches the expected pattern.
 */
public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String dateValue, ConstraintValidatorContext context) {
        if (dateValue == null || dateValue.trim().isEmpty()) {
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(dateValue, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

