package com.peratrack.domain.models;

public class UserParams {
    public String login;
    public String password;

    public UserParams(
            String login,
            String password
    ) {
        this.login = login;
        this.password = password;
    }
}
