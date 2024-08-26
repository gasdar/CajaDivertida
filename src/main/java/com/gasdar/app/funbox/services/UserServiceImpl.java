package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasdar.app.funbox.models.User;
import com.gasdar.app.funbox.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly=true)
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<User> findById(ObjectId id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

}
