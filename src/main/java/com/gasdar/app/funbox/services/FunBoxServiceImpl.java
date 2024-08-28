package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.gasdar.app.funbox.models.FunBox;
import com.gasdar.app.funbox.repositories.FunBoxRepository;
import com.gasdar.app.funbox.validators.FunBoxValidator;

@Service
public class FunBoxServiceImpl implements FunBoxService {

    @Autowired
    private FunBoxRepository repository;

    @Autowired
    private FunBoxValidator validator;

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
    public Optional<FunBox> update(FunBox funBox, ObjectId id) {
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

    @Override
    public void validateData(FunBox box, BindingResult bindingResult, ObjectId userId) {
        validator.validate(box, bindingResult, userId);
    }

    @Override
    public boolean assignOthersValues(FunBox box) {
        return validator.assignOthersValues(box);
    }

    
}
