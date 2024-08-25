package com.gasdar.app.caja_divertida.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/products")
public class ProductoController {



    @GetMapping
    public ResponseEntity<?> listAll() {

    }

}
