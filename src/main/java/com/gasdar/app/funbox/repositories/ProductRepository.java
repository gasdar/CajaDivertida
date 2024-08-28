package com.gasdar.app.funbox.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gasdar.app.funbox.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    // Consulta personalizada con proyección y operadores
    @Query(value="{ 'price': { $gte: ?0, $lte: ?1 } }")
    long countBetweenMinAndMax(int min, int max);

    // Consulta automática generada por Spring Data MongoDB
    List<Product> findByPriceBetween(int min, int max);

}
