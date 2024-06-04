package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.FavoriteBuild;
import com.mobabuild.api_build.entities.User;

import java.util.List;
import java.util.Optional;

public interface IFavoriteBuildDAO {

    List<FavoriteBuild> findAll();

    Optional<FavoriteBuild> findById(Long id);

    void save(FavoriteBuild favoriteBuild);

    void deleteById(Long id);
}
