package com.gasdar.app.caja_divertida.services;

import java.util.List;
import java.util.Optional;

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
    public Optional<Producto> findById(Integer id) {
        return repository.findById(id);
    }

    
}
