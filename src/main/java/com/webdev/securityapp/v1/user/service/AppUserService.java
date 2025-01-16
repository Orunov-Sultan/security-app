package com.webdev.securityapp.v1.user.service;

import com.webdev.securityapp.v1.user.dto.AppUserDto;

import java.util.List;

public interface AppUserService {
    AppUserDto save(AppUserDto appUserDto);
    AppUserDto findById(Long id);
    List<AppUserDto> findAll();
    List<AppUserDto> findAllDeActiveUsers();
    AppUserDto updateUser(Long id, AppUserDto appUserDto);
    AppUserDto reActivateUser(Long id);
    void delete(Long id);
}
