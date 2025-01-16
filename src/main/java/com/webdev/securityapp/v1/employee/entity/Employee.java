package com.webdev.securityapp.v1.employee.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private Long role_id;
    private Long last_action_id;

    @Column(updatable = false)
    private LocalDateTime created_at;
    private LocalDateTime last_login;
    private Boolean is_deleted;

    public Employee() {
    }

    public Employee(String login, String password, Long role_id, Long last_action_id, LocalDateTime created_at, LocalDateTime last_login, Boolean is_deleted) {
        this.login = login;
        this.password = password;
        this.role_id = role_id;
        this.last_action_id = last_action_id;
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

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getLast_action_id() {
        return last_action_id;
    }

    public void setLast_action_id(Long last_action_id) {
        this.last_action_id = last_action_id;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role_id=" + role_id +
                ", last_action_id=" + last_action_id +
                ", created_at=" + created_at +
                ", last_login=" + last_login +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
