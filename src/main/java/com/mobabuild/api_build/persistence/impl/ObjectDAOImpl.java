package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.persistence.IObjectDAO;
import com.mobabuild.api_build.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ObjectDAOImpl implements IObjectDAO {

    @Autowired
    private ObjectRepository objectRepository;

    @Override
    public List<Object> findAll() {
        return (List<Object>) objectRepository.findAll();
    }

    @Override
    public Optional<Object> findById(Long id) {
        return objectRepository.findById(id);
    }

    @Override
    public void save(Object object) {
        objectRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        objectRepository.deleteById(id);
    }

    @Override
    public int setObject(String name) {
        return objectRepository.setObject(name);
    }

    @Override
    public boolean  updateObjectName(Long id, String name) {
        int rowsAffected = objectRepository.updateObjectName(id, name);
        return rowsAffected > 0;
    }
}
