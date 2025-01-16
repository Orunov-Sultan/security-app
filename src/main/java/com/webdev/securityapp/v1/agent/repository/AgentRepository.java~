package com.webdev.securityapp.v1.user.repository;

import com.webdev.securityapp.v1.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = false")
    List<AppUser> findAllActiveUsers();

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = true")
    List<AppUser> findAllDeActivatedUsers();

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = false AND u.id = :id")
    AppUser findActiveUserById(Long id);

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = true AND u.id = :id")
    AppUser findDeActivatedUserById(Long id);
}
