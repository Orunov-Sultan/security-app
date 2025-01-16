package com.webdev.securityapp.v1.user.service;

import com.webdev.securityapp.v1.user.dto.LoginDto;
import com.webdev.securityapp.v1.user.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
