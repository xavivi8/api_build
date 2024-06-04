package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.Spell;

import java.util.List;
import java.util.Optional;

public interface ISpellDAO {

    List<Spell> findAll();

    Optional<Spell> findById(Long id);

    void save(Spell spell);

    void deleteById(Long id);
}
