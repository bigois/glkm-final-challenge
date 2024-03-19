package br.com.fiap.techchallenge5authenticatelogin.controller;


import br.com.fiap.techchallenge5authenticatelogin.entity.User;
import br.com.fiap.techchallenge5authenticatelogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
