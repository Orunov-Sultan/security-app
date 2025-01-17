package com.webdev.securityapp.v1.employee.controller;

import com.webdev.securityapp.v1.employee.dto.EmployeeDto;
import com.webdev.securityapp.v1.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees= employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/deactivated-employees")
    public ResponseEntity<List<EmployeeDto>> getAllDeactivatedEmployees() {
        List<EmployeeDto> employeesDto = employeeService.findAllDeActiveEmployees();
        if (employeesDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeesDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/reactivate/{id}")
    public ResponseEntity<EmployeeDto> reActivateEmployee(@PathVariable Long id) {
        EmployeeDto reActivatedEmployee = employeeService.reActivateEmployee(id);
        return new ResponseEntity<>(reActivatedEmployee, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>("Employee with the id: '" + id + "' has been deleted", HttpStatus.OK);
    }
}
