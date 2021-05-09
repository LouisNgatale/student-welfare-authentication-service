package com.louisngatale.authenticationservice.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class AuthenticationResponse {
    private String jwt;
    private String fullName;
    private String username;
    private Set<SimpleGrantedAuthority> roles ;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<SimpleGrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<SimpleGrantedAuthority> roles) {
        this.roles = roles;
    }

    public AuthenticationResponse(String jwt, String fullName, String username, Set<SimpleGrantedAuthority> roles) {
        this.jwt = jwt;
        this.fullName = fullName;
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
