package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.SpellSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellSetRepository extends CrudRepository<SpellSet, Long> {
}
