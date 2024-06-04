package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.ObjectSet;

import java.util.List;
import java.util.Optional;

public interface IObjectSetDao {

    List<ObjectSet> findAll();

    Optional<ObjectSet> findById(Long id);

    void save(ObjectSet objectSet);

    void deleteById(Long id);
}
