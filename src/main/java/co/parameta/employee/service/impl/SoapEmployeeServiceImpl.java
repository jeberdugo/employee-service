package co.parameta.employee.service.impl;

import co.parameta.employee.domain.model.Employee;
import co.parameta.employee.service.SoapEmployeeService;
import co.parameta.employee.soap.mapper.EmployeeSoapMapper;
import co.parameta.employee.soap.model.EmployeeSoap;
import co.parameta.employee.soap.model.SaveEmployeeRequest;
import co.parameta.employee.soap.model.SaveEmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Implementation of SOAP Employee Service.
 * Makes SOAP client calls to save employees.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SoapEmployeeServiceImpl implements SoapEmployeeService {
    
    private final WebServiceTemplate webServiceTemplate;
    private final EmployeeSoapMapper employeeSoapMapper;
    
    @Override
    public Employee saveEmployee(Employee employee) {
        log.info("Calling SOAP service to save employee: {}", employee.getDocumentNumber());
        
        try {
            // Map Employee entity to SOAP model
            EmployeeSoap employeeSoap = employeeSoapMapper.toSoap(employee);
            
            // Create SOAP request
            SaveEmployeeRequest request = SaveEmployeeRequest.builder()
                    .employee(employeeSoap)
                    .build();
            
            // Call SOAP endpoint
            SaveEmployeeResponse response = (SaveEmployeeResponse) webServiceTemplate
                    .marshalSendAndReceive(request);
            
            log.info("SOAP service call successful. Employee saved with ID: {}", 
                    response.getEmployee().getId());
            
            // Map SOAP response back to Employee entity
            return employeeSoapMapper.toEntity(response.getEmployee());
            
        } catch (Exception e) {
            log.error("Error calling SOAP service for employee: {}", 
                    employee.getDocumentNumber(), e);
            throw new RuntimeException("Failed to save employee via SOAP service: " + e.getMessage(), e);
        }
    }
}

