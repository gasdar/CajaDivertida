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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasdar.app.funbox.helpers.InfoHelper;
import com.gasdar.app.funbox.models.FunBox;
import com.gasdar.app.funbox.services.FunBoxService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/app/boxes")
public class FunBoxController {

    @Autowired
    private FunBoxService service;

    @Value("${response.notfound}")
    private String messageNF;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ResponseEntity<?> notFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfoHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
        if(id.length() == 24) {
            ObjectId boxId = new ObjectId(id);
            Optional<FunBox> optionalBox = service.findById(boxId);
            if(optionalBox.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalBox.get());
            }
        }
        return notFound;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FunBox box) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(box));
    }

    // LÓGICA POR HACER
    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody FunBox box) {
        return null;
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        ResponseEntity<?> notFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfoHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
        if(id.length() == 24) {
            ObjectId boxId = new ObjectId(id);
            Optional<FunBox> optionalBox = service.delete(boxId);
            if(optionalBox.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalBox.get());
            }
        }
        return notFound;
    }
    

}
