package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.RuneSet;
import com.mobabuild.api_build.persistence.IRuneSetDAO;
import com.mobabuild.api_build.repository.RuneSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RuneSetDAOImpl implements IRuneSetDAO {

    @Autowired
    private RuneSetRepository runeSetRepository;

    @Override
    public List<RuneSet> findAll() {
        return (List<RuneSet>) runeSetRepository.findAll();
    }

    @Override
    public Optional<RuneSet> findById(Long id) {
        return runeSetRepository.findById(id);
    }

    @Override
    public void save(RuneSet runeSet) {
        runeSetRepository.save(runeSet);
    }

    @Override
    public void deleteById(Long id) {
        runeSetRepository.deleteById(id);
    }
}
