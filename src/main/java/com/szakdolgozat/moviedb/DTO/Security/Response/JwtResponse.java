package com.szakdolgozat.moviedb.DTO.Security.Response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String username;
    private String email;
    private Integer id;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    private List<String> roles;

    public JwtResponse(String token, String username, String email, Integer id, List<String> roles) {

        this.token = token;
        this.username = username;
        this.email = email;
        this.id = id;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
