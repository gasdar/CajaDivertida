package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.gasdar.app.funbox.models.Product;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(ObjectId id);
    Product save(Product product);
    Optional<Product> update(ObjectId id, Product product);
    Optional<Product> delete(ObjectId id);

}
