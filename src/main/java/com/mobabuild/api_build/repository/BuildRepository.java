package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Build;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildRepository extends CrudRepository<Build, Long> {

    @Query("SELECT b FROM Build b WHERE b.champions.id = ?1")
    List<Build> findByChampionsId(Long championsId);

    @Query("SELECT b FROM Build b WHERE b.user.id = ?1")
    List<Build> findByUserId(Long userId);

}
