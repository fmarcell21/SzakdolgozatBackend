package com.szakdolgozat.moviedb.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userid")
    private Set<Movieprogress> movieprogresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userid")
    private Set<Person> people = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userid")
    private Set<Tvprogress> tvprogresses = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Movieprogress> getMovieprogresses() {
        return movieprogresses;
    }

    public void setMovieprogresses(Set<Movieprogress> movieprogresses) {
        this.movieprogresses = movieprogresses;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Tvprogress> getTvprogresses() {
        return tvprogresses;
    }

    public void setTvprogresses(Set<Tvprogress> tvprogresses) {
        this.tvprogresses = tvprogresses;
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

}