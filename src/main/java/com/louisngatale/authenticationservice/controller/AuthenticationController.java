package com.louisngatale.authenticationservice.controller;

import com.louisngatale.authenticationservice.models.AuthenticationRequest;
import com.louisngatale.authenticationservice.models.AuthenticationResponse;
import com.louisngatale.authenticationservice.services.TokenProvider;
import com.louisngatale.authenticationservice.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtUtil;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String test(){
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
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

                 System.out.println(jwt);
                 return ResponseEntity.ok(new AuthenticationResponse(jwt));
             }catch (Exception e){
                 System.out.println(e.getMessage());
                 throw new IllegalStateException(e);
             }

    }
}
