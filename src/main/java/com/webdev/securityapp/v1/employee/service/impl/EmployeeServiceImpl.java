package com.webdev.securityapp.v1.employee.service.impl;

import com.webdev.securityapp.v1.employee.dto.EmployeeDto;
import com.webdev.securityapp.v1.employee.entity.Employee;
import com.webdev.securityapp.v1.employee.repository.EmployeeRepository;
import com.webdev.securityapp.v1.employee.service.EmployeeService;
import com.webdev.securityapp.v1.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        employee.setCreated_at(LocalDateTime.now());
        employee.setLast_login(LocalDateTime.now());
        employee.setIs_deleted(false);

        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findActiveEmployeeById(id);

        if (employee == null) {
            throw new ResourceNotFoundException("Employee with the id: '" + id + "' does not exist");
        }

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAllEmployees();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();
    }

    @Override
    public List<EmployeeDto> findAllDeActiveEmployees() {
        List<Employee> employees = employeeRepository.findAllDeActivatedEmployees();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findActiveEmployeeById(id);

        if (employee == null) {
            throw new ResourceNotFoundException("Employee with the id: '" + id + "' does not exist");
        }

        employee.setLogin(employeeDto.getLogin());
        employee.setPassword(employeeDto.getPassword());

        Employee updatedEmployee = employeeRepository.save(employee);

        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto reActivateEmployee(Long id) {
        Employee employee = employeeRepository.findDeActivatedEmployeeById(id);

        if (employee == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        employee.setIs_deleted(false);

        Employee activatedEmployee = employeeRepository.save(employee);

        return modelMapper.map(activatedEmployee, EmployeeDto.class);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findActiveEmployeeById(id);

        if (employee == null) {
            throw new ResourceNotFoundException("Employee with the id: '" + id + "' does not exist");
        }
        employee.setIs_deleted(true);
        employeeRepository.save(employee);
    }
}
