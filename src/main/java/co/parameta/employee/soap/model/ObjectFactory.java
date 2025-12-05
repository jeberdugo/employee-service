package co.parameta.employee.soap.model;

import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * ObjectFactory for SOAP model classes.
 * Required by JAXB for marshalling/unmarshalling.
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances
     * of schema derived classes.
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaveEmployeeRequest}.
     */
    public SaveEmployeeRequest createSaveEmployeeRequest() {
        return new SaveEmployeeRequest();
    }

    /**
     * Create an instance of {@link SaveEmployeeResponse}.
     */
    public SaveEmployeeResponse createSaveEmployeeResponse() {
        return new SaveEmployeeResponse();
    }

    /**
     * Create an instance of {@link EmployeeSoap}.
     */
    public EmployeeSoap createEmployeeSoap() {
        return new EmployeeSoap();
    }
}

