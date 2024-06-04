package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.FavoriteBuild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteBuildRepository extends CrudRepository<FavoriteBuild, Long> {
}
