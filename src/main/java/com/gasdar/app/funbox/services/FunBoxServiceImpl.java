package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasdar.app.funbox.models.FunBox;
import com.gasdar.app.funbox.repositories.FunBoxRepository;

@Service
public class FunBoxServiceImpl implements FunBoxService {

    @Autowired
    private FunBoxRepository repository;

    @Transactional(readOnly=true)
    @Override
    public List<FunBox> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<FunBox> findById(ObjectId id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public FunBox save(FunBox funBox) {
       return repository.save(funBox);
    }

    @Transactional
    @Override
    public Optional<FunBox> update(ObjectId id, FunBox funBox) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<FunBox> delete(ObjectId id) {
        Optional<FunBox> optionalBox = repository.findById(id);
        optionalBox.ifPresent(box -> {
            repository.delete(box);
        });
        return optionalBox;
    }

}
