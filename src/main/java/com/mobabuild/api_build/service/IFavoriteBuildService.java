package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.FavoriteBuild;

import java.util.List;
import java.util.Optional;

public interface IFavoriteBuildService {

    List<FavoriteBuild> findAll();

    Optional<FavoriteBuild> findById(Long id);

    void save(FavoriteBuild favoriteBuild);

    void deleteById(Long id);
}
