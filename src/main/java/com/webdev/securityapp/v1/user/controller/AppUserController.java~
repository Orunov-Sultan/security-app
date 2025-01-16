package com.webdev.securityapp.v1.users.controller;

import com.webdev.securityapp.v1.users.dto.AppUserDto;
import com.webdev.securityapp.v1.users.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AppUserDto> addUser(@RequestBody AppUserDto appUserDto) {
        return new ResponseEntity<>(appUserService.save(appUserDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDto> getUser(@PathVariable Long id) {
        AppUserDto appUserDto = appUserService.findById(id);
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getAllUsers() {
        List<AppUserDto> appUsersDto = appUserService.findAll();
        return ResponseEntity.ok(appUsersDto);
    }

    @GetMapping("/deactivated-users")
    public ResponseEntity<List<AppUserDto>> getAllDeactivatedUsers() {
        List<AppUserDto> appUsersDto = appUserService.findAllDeActiveUsers();
        return ResponseEntity.ok(appUsersDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDto> updateUser(@PathVariable Long id, @RequestBody AppUserDto appUserDto) {
        AppUserDto updatedUser = appUserService.updateUser(id, appUserDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/reactivate/{id}")
    public ResponseEntity<AppUserDto> reActivateUser(@PathVariable Long id) {
        AppUserDto reActivatedUser = appUserService.reActivateUser(id);
        return new ResponseEntity<>(reActivatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        appUserService.delete(id);
        return new ResponseEntity<>("User with the id: '" + id + "' has been deleted", HttpStatus.OK);
    }
}
