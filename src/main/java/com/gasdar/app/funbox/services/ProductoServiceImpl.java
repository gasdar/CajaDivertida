package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.gasdar.app.funbox.models.Product;
import com.gasdar.app.funbox.repositories.ProductRepository;
import com.gasdar.app.funbox.validators.ProductValidator;

@Service
public class ProductoServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductValidator validator;

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
    public List<Product> saveAll(List<Product> prods) {
        return repository.saveAll(prods);
    }

    @Transactional
    @Override
    public Optional<Product> update(Product producto, ObjectId id) {
        Optional<Product> optionalProd = this.findById(id);
        if(optionalProd.isPresent()) {
            Product prod = optionalProd.get();
            prod.setName(producto.getName());
            prod.setDesc(producto.getDesc());
            prod.setCategory(producto.getCategory());
            prod.setPrice(producto.getPrice());
            prod.setStock(producto.getStock());
            return Optional.of(repository.save(prod));
        }
        return optionalProd;
    }

    @Transactional
    @Override
    public Optional<Product> delete(ObjectId id) {
        Optional<Product> optionalProd = repository.findById(id);
        if(optionalProd.isPresent()) {
            Product prod = optionalProd.get();
            repository.delete(prod);
            return Optional.of(prod);
        }
        return optionalProd;
    }

    @Override
    public void validateInfo(Product product, BindingResult bindingResult) {
        validator.validate(product, bindingResult);
    }

    @Override
    public void validateInfoList(BindingResult bindingResult, List<Product> prods) {
        validator.validateList(bindingResult, prods);
    }
    
}
