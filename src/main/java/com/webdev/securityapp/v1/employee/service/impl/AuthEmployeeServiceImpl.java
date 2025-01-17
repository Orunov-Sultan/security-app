package com.webdev.securityapp.v1.employee.service.impl;

import com.webdev.securityapp.v1.employee.dto.LoginEmployeeDto;
import com.webdev.securityapp.v1.employee.dto.RegisterEmployeeDto;
import com.webdev.securityapp.v1.employee.service.AuthEmployeeService;
import com.webdev.securityapp.v1.exception.LoginAlreadyExistsException;
import com.webdev.securityapp.v1.security.JwtTokenProvider;
import com.webdev.securityapp.v1.user.entity.Role;
import com.webdev.securityapp.v1.user.entity.User;
import com.webdev.securityapp.v1.user.repository.RoleRepository;
import com.webdev.securityapp.v1.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AuthEmployeeServiceImpl implements AuthEmployeeService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthEmployeeServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public String login(LoginEmployeeDto loginEmployeeDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginEmployeeDto.getLogin(),
                        loginEmployeeDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterEmployeeDto registerEmployeeDto) {
        //Проверка на уникальность логина
        if (userRepository.existsByLogin(registerEmployeeDto.getLogin())) {
            throw new LoginAlreadyExistsException("User with login: '" + registerEmployeeDto.getLogin() + "' already exists");
        }

        //Создание пользователя
        User user = new User();
        user.setLogin(registerEmployeeDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerEmployeeDto.getPassword()));
        user.setCreated_at(LocalDateTime.now());
        user.setLast_login(LocalDateTime.now());
        user.setIs_deleted(false);

        //Присвоение роли
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_EMPLOYEE").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "Employee registered successfully!";
    }
}
