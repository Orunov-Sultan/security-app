package com.webdev.securityapp.v1.users.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "app_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Column(updatable = false)
    private LocalDateTime created_at;
    private LocalDateTime last_login;
    private Boolean is_deleted;

    public AppUser() {
    }

    public AppUser(String login, String password, LocalDateTime created_at, LocalDateTime last_login, Boolean is_deleted) {
        this.login = login;
        this.password = password;
        this.created_at = created_at;
        this.last_login = last_login;
        this.is_deleted = is_deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

}
