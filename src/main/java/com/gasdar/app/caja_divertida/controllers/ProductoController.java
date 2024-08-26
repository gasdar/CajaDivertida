package com.gasdar.app.caja_divertida.controllers;

import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.gasdar.app.caja_divertida.helpers.InfoHelper;
import com.gasdar.app.caja_divertida.models.Producto;
import com.gasdar.app.caja_divertida.services.ProductoService;

@RestController
@RequestMapping("/app/products")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable ObjectId id) {
        Optional<Producto> optionalProd = service.findById(id);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("product", optionalProd.get(), "id", optionalProd.get().getId().toString()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfoHelper.infoResponse("El registro no fue encontrado", HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Producto producto) {
        producto.setStock(100);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@PathVariable ObjectId id, @RequestBody Producto producto) {
        Optional<Producto> optionalProd = service.update(id, producto);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProd.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfoHelper.infoResponse("El registro no se pudo actualizar", HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id) {
        Optional<Producto> optionalProd = service.deleteById(id);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProd.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfoHelper.infoResponse("El registro no se pudo eliminar", HttpStatus.NOT_FOUND.value()));
    }

}
