package com.gasdar.app.caja_divertida.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasdar.app.caja_divertida.models.Producto;
import com.gasdar.app.caja_divertida.services.ProductoService;

@RestController
@RequestMapping("/app/products")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Producto> optionalProd = service.findById(id);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProd.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
