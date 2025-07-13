package com.workflow.controllers;

import com.workflow.entities.User;
import com.workflow.services.UserDetailsServiceImp;
import com.workflow.services.UserService;
import com.workflow.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImp userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("health-check")
    String healthCheck(){
        return "ok";
    }
    @PostMapping("signup")
    ResponseEntity<String> signup(@RequestBody User user){
        if (user!=null){
            boolean b = userService.saveUser(user);
            if (b) return ResponseEntity.ok().body("Account created");
        }
        return ResponseEntity.badRequest().body("Something went wrong");
    }
    @PostMapping("login")
    ResponseEntity<String> login(@RequestBody User user){
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password are required.");
        }
        try {
            String username = user.getEmail().toLowerCase();
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticate.getName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
}
