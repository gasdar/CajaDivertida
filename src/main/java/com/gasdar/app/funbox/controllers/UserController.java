package com.gasdar.app.funbox.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasdar.app.funbox.helpers.InfoHelper;
import com.gasdar.app.funbox.models.User;
import com.gasdar.app.funbox.services.UserService;

@RestController
@RequestMapping(value="/app/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ResponseEntity<?> notFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfoHelper.infoResponse("El registro no fue encontrado", HttpStatus.NOT_FOUND.value()));
        if(id.length() != 24) {
            return notFound;
        }
        ObjectId idUser = new ObjectId(id);
        Optional<User> optionalUser = service.findById(idUser);
        if(optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
        }
        return notFound;
    }

    @PostMapping
    public ResponseEntity<?> findById(@RequestBody User user) {
        user.setSalary(10000000);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

}
