package com.javatechie.service;

import com.javatechie.entity.UserCredential;
import com.javatechie.exception.CustomerNotFoundException;
import com.javatechie.repository.UserCredentialRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) throws CustomerNotFoundException {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        Optional<UserCredential> user = repository.findByEmail(credential.getEmail());
        if(user.isEmpty()) {
        	repository.save(credential);
            return "user added to the system";
        }else {
        	throw new CustomerNotFoundException("User Already exists with email");
        }
        
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token, UserDetails cred) {
        jwtService.validateToken(token, cred);
    }


}
