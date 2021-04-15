package com.louisngatale.authenticationservice.models;

import com.louisngatale.authenticationservice.entities.Roles;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationResponse {
    private String jwt;
    private String fullName;
    private String username;
    private String roles ;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public AuthenticationResponse(String jwt, String fullName, String username, String roles) {
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
