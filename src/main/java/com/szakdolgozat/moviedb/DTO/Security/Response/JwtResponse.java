package com.szakdolgozat.moviedb.DTO.Security.Response;

public class JwtResponse {
    private String token;
    private String username;
    private String email;
    private Integer id;

    public JwtResponse(String token, String username, String email, Integer id) {

        this.token = token;
        this.username = username;
        this.email = email;
        this.id = id;
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
