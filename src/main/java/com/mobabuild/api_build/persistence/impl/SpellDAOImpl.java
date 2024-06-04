package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.persistence.ISpellDAO;
import com.mobabuild.api_build.repository.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpellDAOImpl implements ISpellDAO {

    @Autowired
    private SpellRepository spellRepository;

    @Override
    public List<Spell> findAll() {
        return (List<Spell>) spellRepository.findAll();
    }

    @Override
    public Optional<Spell> findById(Long id) {
        return spellRepository.findById(id);
    }

    @Override
    public void save(Spell spell) {
        spellRepository.save(spell);
    }

    @Override
    public void deleteById(Long id) {
        spellRepository.deleteById(id);
    }
}
