package co.parameta.employee.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SOAP request wrapper for saving an employee.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"employee"})
@XmlRootElement(name = "SaveEmployeeRequest", namespace = "http://parameta.co/employee/soap")
public class SaveEmployeeRequest {

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private EmployeeSoap employee;
}

