package com.webdev.securityapp.v1.user.controller;


import com.webdev.securityapp.v1.user.dto.UserDto;
import com.webdev.securityapp.v1.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> appUsersDto = userService.findAll();
        return ResponseEntity.ok(appUsersDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @GetMapping("/deactivated-users")
    public ResponseEntity<List<UserDto>> getAllDeactivatedUsers() {
        List<UserDto> appUsersDto = userService.findAllDeActiveUsers();
        if (appUsersDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appUsersDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @PutMapping("/reactivate/{id}")
    public ResponseEntity<UserDto> reActivateUser(@PathVariable Long id) {
        UserDto reActivatedUser = userService.reActivateUser(id);
        return new ResponseEntity<>(reActivatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("User with the id: '" + id + "' has been deleted", HttpStatus.OK);
    }
}
