package com.webdev.securityapp.v1.employee.dto;

public class LoginEmployeeDto {
    private String login;
    private String password;

    public LoginEmployeeDto() {
    }

    public LoginEmployeeDto(String login, String password) {
        this.login = login;
        this.password = password;
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
}
