package br.com.fiap.techchallenge5authenticatelogin.controller;


import br.com.fiap.techchallenge5authenticatelogin.entity.User;
import br.com.fiap.techchallenge5authenticatelogin.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<User> users = this.repository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/verify-user/{id}")
    public ResponseEntity<Boolean> verifyUserExists(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(repository.existsById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found")));
    }
}
