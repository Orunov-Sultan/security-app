package com.webdev.securityapp.v1.employee.service;

import com.webdev.securityapp.v1.employee.dto.LoginEmployeeDto;
import com.webdev.securityapp.v1.employee.dto.RegisterEmployeeDto;

public interface AuthEmployeeService {
    String login(LoginEmployeeDto loginEmployeeDto);
    String register(RegisterEmployeeDto registerEmployeeDto);
}
