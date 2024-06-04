package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Spell;

import java.util.List;
import java.util.Optional;

public interface ISpellService {

    List<Spell> findAll();

    Optional<Spell> findById(Long id);

    void save(Spell spell);

    void deleteById(Long id);

    SpellDTO update(SpellComand spellComand);
}
