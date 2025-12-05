package co.parameta.employee.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing an Employee.
 * Contains all basic employee information and validations.
 */
@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "Document type is required")
    @Size(max = 50, message = "Document type cannot exceed 50 characters")
    @Column(name = "document_type", nullable = false, length = 50)
    private String documentType;

    @NotBlank(message = "Document number is required")
    @Size(max = 50, message = "Document number cannot exceed 50 characters")
    @Column(name = "document_number", nullable = false, unique = true, length = 50)
    private String documentNumber;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be a past date")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Hiring date is required")
    @PastOrPresent(message = "Hiring date must be a past or present date")
    @Column(name = "hiring_date", nullable = false)
    private LocalDate hiringDate;

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String position;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Salary must have a maximum of 10 integer digits and 2 decimal places")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    /**
     * Validates that the employee is of legal age (18 years or older).
     * 
     * @return true if the employee is of legal age, false otherwise
     */
    public boolean isOfLegalAge() {
        if (birthDate == null) {
            return false;
        }
        LocalDate legalAgeDate = birthDate.plusYears(18);
        return !LocalDate.now().isBefore(legalAgeDate);
    }
}

