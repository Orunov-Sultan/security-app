package com.webdev.securityapp.v1.users.service.impl;

import com.webdev.securityapp.v1.exception.ResourceNotFoundException;
import com.webdev.securityapp.v1.users.dto.AppUserDto;
import com.webdev.securityapp.v1.users.entity.AppUser;
import com.webdev.securityapp.v1.users.repository.AppUserRepository;
import com.webdev.securityapp.v1.users.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppUserDto save(AppUserDto appUserDto) {
        AppUser user = modelMapper.map(appUserDto, AppUser.class);

        user.setCreated_at(LocalDateTime.now());
        user.setLast_login(LocalDateTime.now());
        user.setIs_deleted(false);

        AppUser savedUser = appUserRepository.save(user);

        return modelMapper.map(savedUser, AppUserDto.class);
    }

    @Override
    public AppUserDto findById(Long id) {
        AppUser user = appUserRepository.findActiveUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        return modelMapper.map(user, AppUserDto.class);
    }

    @Override
    public List<AppUserDto> findAll() {
        List<AppUser> users = appUserRepository.findAllActiveUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, AppUserDto.class))
                .toList();
    }

    @Override
    public List<AppUserDto> findAllDeActiveUsers() {
        List<AppUser> users = appUserRepository.findAllDeActivatedUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, AppUserDto.class))
                .toList();
    }

    @Override
    public AppUserDto updateUser(Long id, AppUserDto appUserDto) {
        AppUser user = appUserRepository.findActiveUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        user.setLogin(appUserDto.getLogin());
        AppUser updatedUser = appUserRepository.save(user);

        return modelMapper.map(updatedUser, AppUserDto.class);
    }

    @Override
    public AppUserDto reActivateUser(Long id) {
        AppUser user = appUserRepository.findDeActivatedUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        user.setIs_deleted(false);

        AppUser activatedUser = appUserRepository.save(user);

        return modelMapper.map(activatedUser, AppUserDto.class);
    }

    @Override
    public void delete(Long id) {
        AppUser user = appUserRepository.findActiveUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }
        user.setIs_deleted(true);
        appUserRepository.save(user);
    }
}
