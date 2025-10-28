package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class AuthController {

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest req) {
    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(auth);

    String jwt = jwtUtils.generateJwtToken(req.getUsername());
    return ResponseEntity.ok(new JwtResponse(jwt, "Bearer"));
  }
}
