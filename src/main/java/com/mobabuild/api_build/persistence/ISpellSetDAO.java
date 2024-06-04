package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.SpellSet;

import java.util.List;
import java.util.Optional;

public interface ISpellSetDAO {

    List<SpellSet> findAll();

    Optional<SpellSet> findById(Long id);

    void save(SpellSet spellSet);

    void deleteById(Long id);
}
