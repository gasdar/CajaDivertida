package com.gasdar.app.funbox.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasdar.app.funbox.helpers.RequestHelper;
import com.gasdar.app.funbox.models.Product;
import com.gasdar.app.funbox.services.ProductService;

@RestController
@RequestMapping(value="/app/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Value("${response.not-found}")
    private String messageNF;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable ObjectId id) {
        Optional<Product> optionalProd = service.findById(id);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("product", optionalProd.get(), "id", optionalProd.get().getId().toString()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product, BindingResult bindingResult) {
        service.validateInfo(product, bindingResult);
        if(bindingResult.hasErrors()) {
            return RequestHelper.getErrorsFromBody(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PostMapping(value="/list")
    public ResponseEntity<?> saveAll(Product prod, BindingResult bindingResult, @RequestBody List<Product> prods) {
        if(prods.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestHelper.infoResponse(messageNF, HttpStatus.BAD_REQUEST.value()));
        }
        service.validateInfoList(bindingResult, prods);
        if(bindingResult.hasErrors()) {
            return RequestHelper.getErrorsFromBody(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(prods));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, BindingResult bindingResult, @PathVariable ObjectId id) {
        service.validateInfo(product, bindingResult);
        if(bindingResult.hasErrors()) {
            return RequestHelper.getErrorsFromBody(bindingResult);
        }
        Optional<Product> optionalProd = service.update(product, id);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProd.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id) {
        Optional<Product> optionalProd = service.delete(id);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProd.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestHelper.infoResponse(messageNF, HttpStatus.NOT_FOUND.value()));
    }

}
