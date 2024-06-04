package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.ObjectSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectSetRepository extends CrudRepository<ObjectSet, Long> {
}
