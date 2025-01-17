package com.webdev.securityapp.v1.user.service.impl;

import com.webdev.securityapp.v1.exception.ResourceNotFoundException;
import com.webdev.securityapp.v1.user.dto.UserDto;
import com.webdev.securityapp.v1.user.entity.Role;
import com.webdev.securityapp.v1.user.entity.User;
import com.webdev.securityapp.v1.user.repository.RoleRepository;
import com.webdev.securityapp.v1.user.repository.UserRepository;
import com.webdev.securityapp.v1.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        user.setCreated_at(LocalDateTime.now());
        user.setLast_login(LocalDateTime.now());
        user.setIs_deleted(false);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // присвоение роли
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findActiveUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAllActiveUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public List<UserDto> findAllDeActiveUsers() {
        List<User> users = userRepository.findAllDeActivatedUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findActiveUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto reActivateUser(Long id) {
        User user = userRepository.findDeActivatedUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }

        user.setIs_deleted(false);

        User activatedUser = userRepository.save(user);

        return modelMapper.map(activatedUser, UserDto.class);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findActiveUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with the id: '" + id + "' does not exist");
        }
        user.setIs_deleted(true);
        userRepository.save(user);
    }
}
