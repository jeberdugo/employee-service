package co.parameta.employee.soap.mapper;

import co.parameta.employee.domain.model.Employee;
import co.parameta.employee.soap.model.EmployeeSoap;
import org.springframework.stereotype.Component;

/**
 * Mapper between SOAP model and JPA entity.
 */
@Component
public class EmployeeSoapMapper {

    /**
     * Maps EmployeeSoap to Employee entity.
     * 
     * @param employeeSoap SOAP model
     * @return JPA entity
     */
    public Employee toEntity(EmployeeSoap employeeSoap) {
        if (employeeSoap == null) {
            return null;
        }

        return Employee.builder()
                .id(employeeSoap.getId())
                .firstName(employeeSoap.getFirstName())
                .lastName(employeeSoap.getLastName())
                .documentType(employeeSoap.getDocumentType())
                .documentNumber(employeeSoap.getDocumentNumber())
                .birthDate(employeeSoap.getBirthDate())
                .hiringDate(employeeSoap.getHiringDate())
                .position(employeeSoap.getPosition())
                .salary(employeeSoap.getSalary())
                .build();
    }

    /**
     * Maps Employee entity to EmployeeSoap.
     * 
     * @param employee JPA entity
     * @return SOAP model
     */
    public EmployeeSoap toSoap(Employee employee) {
        if (employee == null) {
            return null;
        }

        return EmployeeSoap.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .documentType(employee.getDocumentType())
                .documentNumber(employee.getDocumentNumber())
                .birthDate(employee.getBirthDate())
                .hiringDate(employee.getHiringDate())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .build();
    }
}

