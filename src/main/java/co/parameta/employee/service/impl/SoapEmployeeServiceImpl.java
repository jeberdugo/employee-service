package co.parameta.employee.service.impl;

import co.parameta.employee.domain.model.Employee;
import co.parameta.employee.service.SoapEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of SOAP Employee Service.
 * TODO: Implement actual SOAP client call to external service.
 */
@Slf4j
@Service
public class SoapEmployeeServiceImpl implements SoapEmployeeService {
    
    @Override
    public Employee saveEmployee(Employee employee) {
        log.info("Calling SOAP service to save employee: {}", employee.getDocumentNumber());
        
        // TODO: Implement actual SOAP client call
        // For now, this is a placeholder that will be implemented
        // when the SOAP service endpoint is available
        
        // Simulating SOAP call - in real implementation, this would:
        // 1. Create SOAP request
        // 2. Call SOAP endpoint
        // 3. Parse SOAP response
        // 4. Return saved employee
        
        log.warn("SOAP service not yet implemented - this is a placeholder");
        
        // Return employee as-is (in real scenario, SOAP service would return saved employee with ID)
        return employee;
    }
}

