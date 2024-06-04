package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.persistence.IBuildDAO;
import com.mobabuild.api_build.repository.BuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BuildDAOImpl implements IBuildDAO {

    @Autowired
    private BuildRepository buildRepository;

    @Override
    public List<Build> findAll() {
        return (List<Build>) buildRepository.findAll();
    }

    @Override
    public Optional<Build> findById(Long id) {
        return buildRepository.findById(id);
    }

    @Override
    public void save(Build build) {
        buildRepository.save(build);
    }

    @Override
    public void deleteById(Long id) {
        buildRepository.deleteById(id);
    }

    @Override
    public List<Build> findByChampionsId(Long championsId) {
        return buildRepository.findByChampionsId(championsId);
    }

    @Override
    public List<Build> findByUserId(Long userId) {
        return buildRepository.findByUserId(userId);
    }
}
