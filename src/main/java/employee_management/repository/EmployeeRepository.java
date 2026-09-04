package employee_management.repository;


import employee_management.entity.Department;
import employee_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);


    @Query("""
            SELECT e
            FROM employees e
            WHERE e.salary > :salary
            """
    )
    List<Employee> findHighSalaryEmployeesNative(
            @Param("salary") double salary);
    List<Employee> findEmployeesWithSalaryGreaterThan(
            @Param("salary") double salary);

    @Query("""
       SELECT e
       FROM Employee e
       WHERE e.department = :department 
       """)
    List<Employee> getHighestPaidEmployees(
            String department, @Param("salary") double salary);

    List<Employee> findByDepartment(Department department);


    Optional<Employee> findByEmail(String email);

    List<Employee> findBySalaryGreaterThan(Double salary);

}