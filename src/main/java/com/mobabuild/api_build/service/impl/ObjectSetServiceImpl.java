package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.ObjectSet;
import com.mobabuild.api_build.persistence.IObjectSetDao;
import com.mobabuild.api_build.service.IObjectSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectSetServiceImpl implements IObjectSetService {

    @Autowired
    private IObjectSetDao objectSetDao;

    @Override
    public List<ObjectSet> findAll() {
        return objectSetDao.findAll();
    }

    @Override
    public Optional<ObjectSet> findById(Long id) {
        return objectSetDao.findById(id);
    }

    @Override
    public void save(ObjectSet objectSet) {
        objectSetDao.save(objectSet);
    }

    @Override
    public void deleteById(Long id) {
        objectSetDao.deleteById(id);
    }
}
