package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.validation.BindingResult;

import com.gasdar.app.funbox.models.Product;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(ObjectId id);
    Product save(Product product);
    List<Product> saveAll(List<Product> prods);
    Optional<Product> update(Product product, ObjectId id);
    Optional<Product> delete(ObjectId id);
    void validateInfo(Product product, BindingResult bindingResult);
    void validateInfoList(BindingResult bindingResult, List<Product> prods);

}
