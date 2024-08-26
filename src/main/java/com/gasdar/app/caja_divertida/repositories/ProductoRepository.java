package com.gasdar.app.caja_divertida.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gasdar.app.caja_divertida.models.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, ObjectId> {

    

}
