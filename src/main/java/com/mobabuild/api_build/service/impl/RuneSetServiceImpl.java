package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.RuneSet;
import com.mobabuild.api_build.persistence.IRuneSetDAO;
import com.mobabuild.api_build.service.IRuneSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuneSetServiceImpl implements IRuneSetService {

    @Autowired
    private IRuneSetDAO runeSetDAO;

    @Override
    public List<RuneSet> findAll() {
        return runeSetDAO.findAll();
    }

    @Override
    public Optional<RuneSet> findById(Long id) {
        return runeSetDAO.findById(id);
    }

    @Override
    public void save(RuneSet runeSet) {
        runeSetDAO.save(runeSet);
    }

    @Override
    public void deleteById(Long id) {
        runeSetDAO.deleteById(id);
    }
}
