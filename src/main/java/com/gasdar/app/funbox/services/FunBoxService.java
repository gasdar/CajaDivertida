package com.gasdar.app.funbox.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.gasdar.app.funbox.models.FunBox;

public interface FunBoxService {

    List<FunBox> findAll();
    Optional<FunBox> findById(ObjectId id);
    FunBox save(FunBox funBox);
    Optional<FunBox> update(FunBox funBox, ObjectId id);
    Optional<FunBox> delete(ObjectId id);

}
