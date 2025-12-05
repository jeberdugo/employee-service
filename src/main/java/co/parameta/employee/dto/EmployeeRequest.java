package co.parameta.employee.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO for creating or updating an employee.
 * Contains necessary validations for input data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private String lastName;

    @NotBlank(message = "Document type is required")
    @Size(max = 50, message = "Document type cannot exceed 50 characters")
    private String documentType;

    @NotBlank(message = "Document number is required")
    @Size(max = 50, message = "Document number cannot exceed 50 characters")
    private String documentNumber;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be a past date")
    private LocalDate birthDate;

    @NotNull(message = "Hiring date is required")
    @PastOrPresent(message = "Hiring date must be a past or present date")
    private LocalDate hiringDate;

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position cannot exceed 100 characters")
    private String position;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Salary must have a maximum of 10 integer digits and 2 decimal places")
    private BigDecimal salary;
}

