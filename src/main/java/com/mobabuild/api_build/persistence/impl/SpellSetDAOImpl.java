package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.SpellSet;
import com.mobabuild.api_build.persistence.ISpellSetDAO;
import com.mobabuild.api_build.repository.SpellSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpellSetDAOImpl implements ISpellSetDAO {

    @Autowired
    private SpellSetRepository spellSetRepository;

    @Override
    public List<SpellSet> findAll() {
        return (List<SpellSet>) spellSetRepository.findAll();
    }

    @Override
    public Optional<SpellSet> findById(Long id) {
        return spellSetRepository.findById(id);
    }

    @Override
    public void save(SpellSet spellSet) {
        spellSetRepository.save(spellSet);
    }

    @Override
    public void deleteById(Long id) {
        spellSetRepository.deleteById(id);
    }
}
