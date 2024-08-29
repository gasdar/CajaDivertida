package com.gasdar.app.funbox.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gasdar.app.funbox.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    // Ejemplos
    @Query(value="{ 'price': { $gte : ?0, $lt : ?1 }, 'stock': { $gte : 5 } }", count=true)
    long countBetweenMinAndMax(int min, int max);
    // Consulta automática generada por Spring Data MongoDB
    List<Product> findByPriceBetween(int min, int max);

    // Consultas Propias
    @Query(value="{ 'price': { $gte: ?0, $lte: ?1 }, 'stock': { $gte: 5 } }", count=true)
    long countProdsBetweenMinAndAvg(int min, int avg);

    @Query(value="{ 'price': { $gt: ?0, $lte: ?1 }, 'stock': { $gte: 5 } }", count=true)
    long countProdsUpperAvgAndBetweenMax(int avg, int max);

}
