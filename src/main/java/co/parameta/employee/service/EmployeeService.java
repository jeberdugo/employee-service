package co.parameta.employee.service;

import co.parameta.employee.domain.model.Employee;
import co.parameta.employee.dto.EmployeeRequest;
import co.parameta.employee.dto.EmployeeResponse;
import co.parameta.employee.exception.EmployeeNotOfLegalAgeException;
import co.parameta.employee.service.SoapEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for employee business logic.
 * Handles validations and orchestrates SOAP service calls.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final SoapEmployeeService soapEmployeeService;
    
    /**
     * Creates an employee by validating business rules and calling SOAP service.
     * 
     * @param employeeRequest employee data to create
     * @return EmployeeResponse with calculated fields
     */
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        log.info("Creating employee with document number: {}", employeeRequest.getDocumentNumber());
        
        // Convert DTO to entity
        Employee employee = mapToEntity(employeeRequest);
        
        // Validate business rules
        validateBusinessRules(employee);
        
        // Call SOAP service to save employee
        Employee savedEmployee = soapEmployeeService.saveEmployee(employee);
        
        // Convert to response DTO with calculated fields
        return EmployeeResponse.fromEmployee(savedEmployee);
    }
    
    /**
     * Maps EmployeeRequest DTO to Employee entity.
     * 
     * @param request DTO with employee data
     * @return Employee entity
     */
    private Employee mapToEntity(EmployeeRequest request) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .birthDate(request.getBirthDate())
                .hiringDate(request.getHiringDate())
                .position(request.getPosition())
                .salary(request.getSalary())
                .build();
    }
    
    /**
     * Validates business rules for employee creation.
     * 
     * @param employee employee to validate
     * @throws EmployeeNotOfLegalAgeException if employee is not of legal age
     */
    private void validateBusinessRules(Employee employee) {
        if (!employee.isOfLegalAge()) {
            throw new EmployeeNotOfLegalAgeException(
                    String.format("Employee with document number %s is not of legal age (must be 18 years or older)",
                            employee.getDocumentNumber())
            );
        }
        
        log.debug("Business rules validation passed for employee: {}", employee.getDocumentNumber());
    }
}

