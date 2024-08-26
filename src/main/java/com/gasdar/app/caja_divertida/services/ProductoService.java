package com.gasdar.app.caja_divertida.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.gasdar.app.caja_divertida.models.Producto;

public interface ProductoService {

    List<Producto> findAll();
    Optional<Producto> findById(ObjectId id);
    Producto save(Producto producto);
    Optional<Producto> update(ObjectId id, Producto producto);
    Optional<Producto> deleteById(ObjectId id);

}
