package com.webdev.securityapp.v1.user.controller;

import com.webdev.securityapp.v1.employee.dto.LoginEmployeeDto;
import com.webdev.securityapp.v1.employee.dto.RegisterEmployeeDto;
import com.webdev.securityapp.v1.employee.service.AuthEmployeeService;
import com.webdev.securityapp.v1.security.JWTAuthResponse;
import com.webdev.securityapp.v1.user.dto.LoginDto;
import com.webdev.securityapp.v1.user.dto.RegisterDto;
import com.webdev.securityapp.v1.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private AuthService authService;
    private AuthEmployeeService authEmployeeService;

    public UserAuthController(AuthService authService, AuthEmployeeService authEmployeeService) {
        this.authService = authService;
        this.authEmployeeService = authEmployeeService;
    }

    @PostMapping("/users/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/employees/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginEmployeeDto loginEmployeeDto) {
        String token = authEmployeeService.login(loginEmployeeDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/employees/register")
    public ResponseEntity<String> register(@RequestBody RegisterEmployeeDto registerEmployeeDto) {
        String response = authEmployeeService.register(registerEmployeeDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
