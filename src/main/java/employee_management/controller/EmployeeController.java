package employee_management.controller;

import employee_management.dto.response.EmployeeResponseDto;
import employee_management.entity.Department;
import employee_management.entity.Employee;
import employee_management.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(
             @RequestBody Employee dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(dto));
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully";
    }

    @GetMapping("/name/{name}")
    public List<EmployeeResponseDto> getEmployeesByName(
            @PathVariable String name) {

        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/count")
    public String getEmployeeCount() {

        long employeeCount = employeeService.getEmployeeCount();

        return "Employee counted successfully : " + employeeCount;
    }

    @GetMapping("/department/{department}")
    public List<EmployeeResponseDto> getEmployeesByDepartment(
            @PathVariable Department department) {

        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/email/{email}")
    public List<EmployeeResponseDto> getEmployeesByEmail(
            @PathVariable String email) {

        return employeeService.getEmployeesByEmail(email);
    }



    @GetMapping("/highest-paid-emp-1")
    public List<EmployeeResponseDto> getHighestPaidEmployees(
            String department,
            double salary) {
        return employeeService.getHighestPaidEmployees(
                        department,
                        salary);
    }

    @GetMapping("/salary/greater/more-than/{salary}")
    public List<EmployeeResponseDto> getEmployeesWithSalaryGreaterThan(
            @PathVariable Double salary) {

        return employeeService.getEmployeesWithSalaryGreaterThan(salary);
    }



}