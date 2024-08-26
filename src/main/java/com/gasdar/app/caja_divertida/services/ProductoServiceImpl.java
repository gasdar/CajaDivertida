package com.gasdar.app.caja_divertida.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasdar.app.caja_divertida.models.Producto;
import com.gasdar.app.caja_divertida.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Transactional(readOnly=true)
    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<Producto> findById(ObjectId id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Transactional
    @Override
    public Optional<Producto> update(ObjectId id, Producto producto) {
        Optional<Producto> optionalProd = this.findById(id);
        if(optionalProd.isPresent()) {
            Producto prod = optionalProd.get();
            prod.setNombre(producto.getNombre());
            prod.setDescripcion(producto.getDescripcion());
            prod.setCategoria(producto.getCategoria());
            prod.setPrecio(producto.getPrecio());
            prod.setStock(100);
            return Optional.of(repository.save(prod));
        }
        return optionalProd;
    }

    @Transactional
    @Override
    public Optional<Producto> deleteById(ObjectId id) {
        Optional<Producto> optionalProd = repository.findById(id);
        if(optionalProd.isPresent()) {
            Producto prod = optionalProd.get();
            repository.delete(prod);
            return Optional.of(prod);
        }
        return optionalProd;
    }
    
}
