package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.gasdar.app.funbox.models.User;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(ObjectId id);
    User save(User user);
    Optional<User> update(ObjectId id, User user);
    Optional<User> delete(ObjectId id);

}
