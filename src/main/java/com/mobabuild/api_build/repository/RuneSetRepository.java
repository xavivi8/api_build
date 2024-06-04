package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.RuneSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuneSetRepository extends CrudRepository<RuneSet, Long> {
}
