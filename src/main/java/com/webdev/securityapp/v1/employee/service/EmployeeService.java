package com.webdev.securityapp.v1.employee.service;

import com.webdev.securityapp.v1.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto findById(Long id);
    List<EmployeeDto> findAll();
    List<EmployeeDto> findAllDeActiveEmployees();
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    EmployeeDto reActivateEmployee(Long id);
    void delete(Long id);
}

