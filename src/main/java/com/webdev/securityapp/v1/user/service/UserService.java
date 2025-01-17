package com.webdev.securityapp.v1.user.service;

import com.webdev.securityapp.v1.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto findById(Long id);
    List<UserDto> findAll();
    List<UserDto> findAllDeActiveUsers();
    UserDto updateUser(Long id, UserDto userDto);
    UserDto reActivateUser(Long id);
    void delete(Long id);
}
