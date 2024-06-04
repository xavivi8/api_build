package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.SpellSet;
import com.mobabuild.api_build.persistence.ISpellSetDAO;
import com.mobabuild.api_build.service.ISpellSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpellSetServiceImpl implements ISpellSetService {

    @Autowired
    private ISpellSetDAO spellSetDAO;


    @Override
    public List<SpellSet> findAll() {
        return spellSetDAO.findAll();
    }

    @Override
    public Optional<SpellSet> findById(Long id) {
        return spellSetDAO.findById(id);
    }

    @Override
    public void save(SpellSet spellSet) {
        spellSetDAO.save(spellSet);
    }

    @Override
    public void deleteById(Long id) {
        spellSetDAO.deleteById(id);
    }
}
