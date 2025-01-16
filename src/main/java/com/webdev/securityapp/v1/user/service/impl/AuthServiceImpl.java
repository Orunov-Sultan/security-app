package com.webdev.securityapp.v1.user.service.impl;

import com.webdev.securityapp.v1.exception.LoginAlreadyExistsException;
import com.webdev.securityapp.v1.user.dto.LoginDto;
import com.webdev.securityapp.v1.user.dto.RegisterDto;
import com.webdev.securityapp.v1.user.entity.Role;
import com.webdev.securityapp.v1.user.entity.User;
import com.webdev.securityapp.v1.user.repository.RoleRepository;
import com.webdev.securityapp.v1.user.repository.UserRepository;
import com.webdev.securityapp.v1.user.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getLogin(),
                loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully!";
    }

    @Override
    public String register(RegisterDto registerDto) {
        // проверка на уникальность логина
        if (userRepository.existsByLogin(registerDto.getLogin())) {
            throw new LoginAlreadyExistsException("User with login: '" + registerDto.getLogin() + "' already exists");
        }

        // создание пользователя
        User user = new User();
        user.setLogin(registerDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setCreated_at(LocalDateTime.now());
        user.setLast_login(LocalDateTime.now());
        user.setIs_deleted(false);

        // присвоение роли
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!";
    }
}
