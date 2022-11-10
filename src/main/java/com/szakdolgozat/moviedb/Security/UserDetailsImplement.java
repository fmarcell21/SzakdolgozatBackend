package com.szakdolgozat.moviedb.Security;
import com.szakdolgozat.moviedb.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImplement implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private User user;

    public UserDetailsImplement(User user, Collection<? extends GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    public static UserDetailsImplement build(User user) {


        return new UserDetailsImplement(user);
    }

    public UserDetailsImplement(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
