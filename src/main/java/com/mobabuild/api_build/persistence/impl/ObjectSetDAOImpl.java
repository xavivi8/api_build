package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.ObjectSet;
import com.mobabuild.api_build.persistence.IObjectSetDao;
import com.mobabuild.api_build.repository.ObjectSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ObjectSetDAOImpl implements IObjectSetDao {

    @Autowired
    private ObjectSetRepository objectSetRepository;

    @Override
    public List<ObjectSet> findAll() {
        return (List<ObjectSet>) objectSetRepository.findAll();
    }

    @Override
    public Optional<ObjectSet> findById(Long id) {
        return objectSetRepository.findById(id);
    }

    @Override
    public void save(ObjectSet objectSet) {
        objectSetRepository.save(objectSet);
    }

    @Override
    public void deleteById(Long id) {
        objectSetRepository.deleteById(id);
    }
}
