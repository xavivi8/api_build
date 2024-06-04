package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.Build;

import java.util.List;
import java.util.Optional;

public interface IBuildDAO {

    List<Build> findAll();

    Optional<Build> findById(Long id);

    void save(Build build);

    void deleteById(Long id);

    List<Build> findByChampionsId(Long championsId);

    List<Build> findByUserId(Long userId);
}
