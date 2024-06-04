package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.SpellSet;

import java.util.List;
import java.util.Optional;

public interface ISpellSetService {

    List<SpellSet> findAll();

    Optional<SpellSet> findById(Long id);

    void save(SpellSet spellSet);

    void deleteById(Long id);
}
