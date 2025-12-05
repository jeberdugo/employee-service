package co.parameta.employee.dto;

import co.parameta.employee.domain.model.Employee;
import co.parameta.employee.domain.model.TimePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO containing employee information
 * along with calculated fields: current age and time with company.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private LocalDate birthDate;
    private LocalDate hiringDate;
    private String position;
    private BigDecimal salary;
    
    // Calculated fields
    private TimePeriod currentAge;
    private TimePeriod timeWithCompany;

    /**
     * Creates an EmployeeResponse from an Employee,
     * automatically calculating age and time with company.
     * 
     * @param employee Employee entity
     * @return EmployeeResponse with all calculated fields
     */
    public static EmployeeResponse fromEmployee(Employee employee) {
        if (employee == null) {
            return null;
        }

        LocalDate currentDate = LocalDate.now();
        TimePeriod age = TimePeriod.calculatePeriod(employee.getBirthDate(), currentDate);
        TimePeriod timeWithCompany = TimePeriod.calculatePeriod(employee.getHiringDate(), currentDate);

        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .documentType(employee.getDocumentType())
                .documentNumber(employee.getDocumentNumber())
                .birthDate(employee.getBirthDate())
                .hiringDate(employee.getHiringDate())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .currentAge(age)
                .timeWithCompany(timeWithCompany)
                .build();
    }
}

