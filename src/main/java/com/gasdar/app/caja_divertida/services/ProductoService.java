package com.gasdar.app.caja_divertida.services;

import java.util.List;
import java.util.Optional;

import com.gasdar.app.caja_divertida.models.Producto;

public interface ProductoService {

    List<Producto> findAll();
    Optional<Producto> findById(Integer id);

}
