package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Champions;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChampionsRepository extends CrudRepository<Champions, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO champions (name) VALUES (:name)", nativeQuery = true)
    int setChampion(String name);
}
