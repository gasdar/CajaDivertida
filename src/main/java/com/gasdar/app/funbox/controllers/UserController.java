package com.gasdar.app.funbox.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasdar.app.funbox.helpers.RequestHelper;
import com.gasdar.app.funbox.models.User;
import com.gasdar.app.funbox.services.UserService;

@RestController
@RequestMapping(value="/app/users")
public class UserController {

    @Autowired
    private UserService service;

    @Value("${response.not-found}")
    private String messageNF;
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        if(id.length() == 24) {
            ObjectId idUser = new ObjectId(id);
            Optional<User> optionalUser = service.findById(idUser);
            if(optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        user.setSalary(10000000);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody User user) {
        if(id.length() == 24) {
            ObjectId userId = new ObjectId(id);
            Optional<User> optionalUser = service.update(userId, user);
            if(optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if(id.length() == 24) {
            ObjectId userId = new ObjectId(id);
            Optional<User> optionalUser = service.delete(userId);
            if(optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
    }

}
