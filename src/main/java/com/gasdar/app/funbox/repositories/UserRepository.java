package com.gasdar.app.funbox.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gasdar.app.funbox.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    

}
