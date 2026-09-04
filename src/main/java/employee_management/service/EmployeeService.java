package employee_management.service;

import employee_management.dto.response.EmployeeResponseDto;
import employee_management.entity.Department;
import employee_management.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(
            Employee requestDto);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<EmployeeResponseDto> getEmployeesByName(String name);

    List<EmployeeResponseDto> getEmployeesByEmail(String email);

    List<EmployeeResponseDto> getEmployeesByDepartment(Department department);

    List<EmployeeResponseDto> getHighestPaidEmployees(
            String department,
            double salary) ;



    List<EmployeeResponseDto> getEmployeesWithSalaryGreaterThan(Double salary);


    long getEmployeeCount();

}