package co.parameta.employee.soap.endpoint;

import co.parameta.employee.domain.model.Employee;
import co.parameta.employee.domain.repository.EmployeeRepository;
import co.parameta.employee.soap.mapper.EmployeeSoapMapper;
import co.parameta.employee.soap.model.SaveEmployeeRequest;
import co.parameta.employee.soap.model.SaveEmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * SOAP endpoint for employee operations.
 * Receives SOAP requests and persists employees to the database.
 */
@Slf4j
@Endpoint
@RequiredArgsConstructor
public class EmployeeSoapEndpoint {

    private static final String NAMESPACE_URI = "http://parameta.co/employee/soap";

    private final EmployeeRepository employeeRepository;
    private final EmployeeSoapMapper employeeSoapMapper;

    /**
     * Handles SaveEmployeeRequest SOAP messages.
     * Maps the SOAP employee to JPA entity and persists it.
     * 
     * @param request SOAP request containing employee data
     * @return SOAP response with saved employee (including generated ID)
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
        log.info("Received SOAP request to save employee: {}", 
                request.getEmployee().getDocumentNumber());

        // Map SOAP model to JPA entity
        Employee employee = employeeSoapMapper.toEntity(request.getEmployee());

        // Persist employee to database
        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee saved successfully with ID: {}", savedEmployee.getId());

        // Map saved entity back to SOAP model
        co.parameta.employee.soap.model.EmployeeSoap employeeSoap = 
                employeeSoapMapper.toSoap(savedEmployee);

        // Build and return SOAP response
        return SaveEmployeeResponse.builder()
                .employee(employeeSoap)
                .build();
    }
}

