package com.louisngatale.authenticationservice.controller;

import com.louisngatale.authenticationservice.entities.Roles;
import com.louisngatale.authenticationservice.entities.User;
import com.louisngatale.authenticationservice.models.AuthenticationRequest;
import com.louisngatale.authenticationservice.models.AuthenticationResponse;
import com.louisngatale.authenticationservice.repositories.UserRepository;
import com.louisngatale.authenticationservice.services.TokenProvider;
import com.louisngatale.authenticationservice.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/login")
public class AuthenticationController {
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider jwtUtil;

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
             try{
                 Authentication authentication = authenticationManager.authenticate(
                         new UsernamePasswordAuthenticationToken(
                                 authenticationRequest.getUsername(),
                                 authenticationRequest.getPassword()
                         ));
                 System.out.println("Confirmed");
                 SecurityContextHolder.getContext().setAuthentication(authentication);

                 final String jwt = jwtUtil.generateToken(authentication);

                 Optional<User> userDetails = userRepository.findByloginId(authenticationRequest.getUsername());

                 String userName = userDetails
                         .get()
                         .getFullName();

                 String authorities = authentication.getAuthorities().stream()
                         .map(GrantedAuthority::getAuthority)
                         .collect(Collectors.joining(","));

                 return ResponseEntity.ok(new AuthenticationResponse(jwt,userName,authentication.getName(),authorities));
             }catch (Exception e){
                 System.out.println(e.getMessage());
                 throw new IllegalStateException(e);
             }

    }
}
