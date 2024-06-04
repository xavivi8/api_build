package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.FavoriteBuild;
import com.mobabuild.api_build.persistence.IFavoriteBuildDAO;
import com.mobabuild.api_build.repository.FavoriteBuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FavoriteBuildDAOImpl implements IFavoriteBuildDAO {

    @Autowired
    private FavoriteBuildRepository favoriteBuildRepository;

    @Override
    public List<FavoriteBuild> findAll() {
        return (List<FavoriteBuild>) favoriteBuildRepository.findAll();
    }

    @Override
    public Optional<FavoriteBuild> findById(Long id) {
        return favoriteBuildRepository.findById(id);
    }

    @Override
    public void save(FavoriteBuild favoriteBuild) {
        favoriteBuildRepository.save(favoriteBuild);
    }

    @Override
    public void deleteById(Long id) {
        favoriteBuildRepository.deleteById(id);
    }
}
