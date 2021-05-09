package com.louisngatale.authenticationservice.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class AppUserDetails implements UserDetails {
    private String userName;
    private String password;
    private boolean active;
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    public AppUserDetails(User user) {
        this.userName = user.getLoginId();
        this.password = user.getPassword();
        System.out.println(user.getRoles());
        user.getRoles().forEach(role -> {
            this.authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });
    }

    public AppUserDetails() {
    }

    @Override
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    //
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
