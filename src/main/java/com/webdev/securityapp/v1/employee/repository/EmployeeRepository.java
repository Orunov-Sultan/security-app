package com.webdev.securityapp.v1.employee.repository;

import com.webdev.securityapp.v1.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.is_deleted = false")
    List<Employee> findAllEmployees();

    @Query("SELECT e FROM Employee e WHERE e.is_deleted = true")
    List<Employee> findAllDeActivatedEmployees();

    @Query("SELECT e FROM Employee e WHERE e.is_deleted = false AND e.id = :id")
    Employee findActiveEmployeeById(Long id);

    @Query("SELECT e FROM Employee e WHERE e.is_deleted = true AND e.id = :id")
    Employee findDeActivatedEmployeeById(Long id);

}
