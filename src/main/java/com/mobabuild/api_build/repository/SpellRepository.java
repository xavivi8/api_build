package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Spell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends CrudRepository<Spell, Long> {
}
