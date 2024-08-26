package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasdar.app.funbox.models.Product;
import com.gasdar.app.funbox.repositories.ProductRepository;

@Service
public class ProductoServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly=true)
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<Product> findById(ObjectId id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product producto) {
        return repository.save(producto);
    }

    @Transactional
    @Override
    public Optional<Product> update(ObjectId id, Product producto) {
        Optional<Product> optionalProd = this.findById(id);
        if(optionalProd.isPresent()) {
            Product prod = optionalProd.get();
            prod.setName(producto.getName());
            prod.setDesc(producto.getDesc());
            prod.setCategory(producto.getCategory());
            prod.setPrice(producto.getPrice());
            prod.setStock(1000);
            return Optional.of(repository.save(prod));
        }
        return optionalProd;
    }

    @Transactional
    @Override
    public Optional<Product> deleteById(ObjectId id) {
        Optional<Product> optionalProd = repository.findById(id);
        if(optionalProd.isPresent()) {
            Product prod = optionalProd.get();
            repository.delete(prod);
            return Optional.of(prod);
        }
        return optionalProd;
    }
    
}
