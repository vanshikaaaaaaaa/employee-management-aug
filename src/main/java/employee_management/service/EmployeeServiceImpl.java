package employee_management.service;

import employee_management.dto.response.EmployeeResponseDto;
import employee_management.entity.Department;
import employee_management.entity.Employee;
import employee_management.exception.EmployeeNotFoundException;
import employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private Employee employee;

    private EmployeeResponseDto convertToDto(Employee employee) {
        this.employee = employee;

        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }

    private Employee convertToEntity(
            Employee dto) {

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setPassword(dto.getPassword());

        return employee;
    }

    @Override
    public EmployeeResponseDto createEmployee(
            Employee requestDto) {


        Employee employee = convertToEntity(requestDto);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return convertToDto(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {


        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found: " + id));

        return convertToDto(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(
            Long id,
            Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + id + " does not exist"
                        ));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        Employee updatedEmployee =
                employeeRepository.save(existingEmployee);

        return convertToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(
                    "Employee with ID " + id + " does not exist"
            );
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeResponseDto> getEmployeesByName(String name) {

        List<Employee> employees =
                employeeRepository.findByName(name);

        employees.forEach(
                employee -> System.out.println(employee.getName())
        );

        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDto> getEmployeesByEmail(String email) {

        Optional<Employee> employees =
                employeeRepository.findByEmail(email);

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "Employee with email '" + email + "' does not exist"
            );
        }

        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public long getEmployeeCount() {
        return employeeRepository.count();
    }

    @Override
    public List<EmployeeResponseDto> getEmployeesByDepartment(
            Department department) {

        List<Employee> employees =
                employeeRepository.findByDepartment(department);

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "No employees found in department '" +
                            department.toUpperCase() + "'"
            );
        }

        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    // 4. JPQL query
    public List<EmployeeResponseDto> getHighestPaidEmployees(
            String department,
            double salary) {

        List<EmployeeResponseDto> highestPaidEmployees = employeeRepository
                .getHighestPaidEmployees(department, salary);
        return highestPaidEmployees;
    }


    @Override
    public List<EmployeeResponseDto> getEmployeesWithSalaryGreaterThan(
            Double salary) {

        List<Employee> employees =
                employeeRepository.findBySalaryGreaterThan(salary);

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "No employees found with salary greater than " + salary
            );
        }

        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


}