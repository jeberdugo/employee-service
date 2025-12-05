package co.parameta.employee.controller;

import co.parameta.employee.dto.EmployeeGetRequest;
import co.parameta.employee.dto.EmployeeRequest;
import co.parameta.employee.dto.EmployeeResponse;
import co.parameta.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Employee operations.
 * Handles HTTP GET requests to create employees.
 */
@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeRestController {
    
    private final EmployeeService employeeService;
    
    /**
     * Creates an employee via GET request.
     * Receives employee attributes as query parameters.
     * 
     * @param request employee data from query parameters
     * @return EmployeeResponse with calculated fields (age and time with company)
     */
    @GetMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid EmployeeGetRequest request) {
        log.info("Received GET request to create employee: {}", request.getDocumentNumber());
        
        // Convert GET request (with string dates) to EmployeeRequest (with LocalDate)
        EmployeeRequest employeeRequest = request.toEmployeeRequest();
        
        // Create employee through service layer
        EmployeeResponse response = employeeService.createEmployee(employeeRequest);
        
        log.info("Employee created successfully: {}", response.getDocumentNumber());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

