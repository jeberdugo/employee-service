package co.parameta.employee.soap.model;

import co.parameta.employee.soap.adapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * SOAP model for Employee.
 * Represents the employee data structure in SOAP messages.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeSoap", namespace = "http://parameta.co/employee/soap")
public class EmployeeSoap {

    @XmlElement(namespace = "http://parameta.co/employee/soap")
    private Long id;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private String firstName;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private String lastName;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private String documentType;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private String documentNumber;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate hiringDate;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private String position;

    @XmlElement(namespace = "http://parameta.co/employee/soap", required = true)
    private BigDecimal salary;
}

