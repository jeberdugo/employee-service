package co.parameta.employee.service;

import co.parameta.employee.domain.model.Employee;

/**
 * Service interface for SOAP operations.
 * This will be implemented to call the SOAP web service.
 */
public interface SoapEmployeeService {
    
    /**
     * Saves an employee via SOAP web service.
     * 
     * @param employee employee to save
     * @return saved employee with generated ID
     */
    Employee saveEmployee(Employee employee);
}

