package co.parameta.employee.exception;

/**
 * Exception thrown when an employee is not of legal age (under 18 years old).
 */
public class EmployeeNotOfLegalAgeException extends ValidationException {
    
    public EmployeeNotOfLegalAgeException(String message) {
        super(message);
    }
}

