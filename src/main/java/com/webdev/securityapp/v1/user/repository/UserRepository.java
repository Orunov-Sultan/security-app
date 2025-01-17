package com.webdev.securityapp.v1.user.repository;

import com.webdev.securityapp.v1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.is_deleted = false")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.is_deleted = true")
    List<User> findAllDeActivatedUsers();

    @Query("SELECT u FROM User u WHERE u.is_deleted = false AND u.id = :id")
    User findActiveUserById(Long id);

    @Query("SELECT u FROM User u WHERE u.is_deleted = true AND u.id = :id")
    User findDeActivatedUserById(Long id);
}
