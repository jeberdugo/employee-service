package co.parameta.employee.dto;

import co.parameta.employee.validation.ValidDateFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Request DTO for GET endpoint parameters.
 * Receives dates as strings and validates their format.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeGetRequest {

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

    @NotBlank(message = "Birth date is required")
    @ValidDateFormat(message = "Birth date format must be yyyy-MM-dd")
    private String birthDate;

    @NotBlank(message = "Hiring date is required")
    @ValidDateFormat(message = "Hiring date format must be yyyy-MM-dd")
    private String hiringDate;

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position cannot exceed 100 characters")
    private String position;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Salary must have a maximum of 10 integer digits and 2 decimal places")
    private BigDecimal salary;

    /**
     * Converts this GET request DTO to an EmployeeRequest DTO.
     * Parses date strings to LocalDate objects.
     * 
     * @return EmployeeRequest with parsed dates
     */
    public EmployeeRequest toEmployeeRequest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedBirthDate = LocalDate.parse(birthDate, formatter);
        LocalDate parsedHiringDate = LocalDate.parse(hiringDate, formatter);

        return EmployeeRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .documentType(documentType)
                .documentNumber(documentNumber)
                .birthDate(parsedBirthDate)
                .hiringDate(parsedHiringDate)
                .position(position)
                .salary(salary)
                .build();
    }
}

