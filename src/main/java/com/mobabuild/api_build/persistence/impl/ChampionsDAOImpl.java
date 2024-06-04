package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.persistence.IChampionsDAO;
import com.mobabuild.api_build.repository.ChampionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChampionsDAOImpl implements IChampionsDAO {

    @Autowired
    private ChampionsRepository championsRepository;

    @Override
    public List<Champions> findAll() {
        return (List<Champions>) championsRepository.findAll();
    }

    @Override
    public Optional<Champions> findById(Long id) {
        return championsRepository.findById(id);
    }

    @Override
    public void save(Champions champions) {
        championsRepository.save(champions);
    }

    @Override
    public void deleteById(Long id) {
        championsRepository.deleteById(id);
    }

    @Override
    public int setChampion(String name) {
        return championsRepository.setChampion(name);
    }
}
