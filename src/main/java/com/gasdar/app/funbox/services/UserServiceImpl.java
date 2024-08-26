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

    @Transactional
    @Override
    public Optional<User> update(ObjectId id, User user) {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()) {
            User userDB = optionalUser.get();
            userDB.setName(user.getName());
            userDB.setPassword(user.getPassword());
            userDB.setProfile(user.getProfile());
            userDB.setEmail(user.getEmail());
            userDB.setPhone(user.getPhone());
            userDB.setSalary(10000000);
            userDB.setBoxesDto(user.getBoxesDto());
            return Optional.of(repository.save(userDB));
        }
        return optionalUser;
    }

    @Transactional
    @Override
    public Optional<User> delete(ObjectId id) {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()) {
            User userDeleted = optionalUser.get();
            repository.delete(userDeleted);
            return Optional.of(userDeleted);
        }
        return optionalUser;
    }

}
