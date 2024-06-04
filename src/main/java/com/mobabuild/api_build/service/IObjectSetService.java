package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.ObjectSet;

import java.util.List;
import java.util.Optional;

public interface IObjectSetService {

    List<ObjectSet> findAll();

    Optional<ObjectSet> findById(Long id);

    void save(ObjectSet objectSet);

    void deleteById(Long id);
}
