package co.parameta.employee.domain.repository;

import co.parameta.employee.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Employee entity.
 * Provides CRUD operations and custom queries.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Finds an employee by document number.
     * 
     * @param documentNumber document number to search
     * @return Optional containing the employee if found
     */
    Optional<Employee> findByDocumentNumber(String documentNumber);
    
    /**
     * Checks if an employee exists with the given document number.
     * 
     * @param documentNumber document number to check
     * @return true if exists, false otherwise
     */
    boolean existsByDocumentNumber(String documentNumber);
}

