package com.webdev.securityapp.v1.user.dto;

public class RegisterDto {
    private String login;
    private String password;

    public RegisterDto() {
    }

    public RegisterDto(String login, String password) {
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
