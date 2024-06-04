package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.FavoriteBuild;
import com.mobabuild.api_build.persistence.IFavoriteBuildDAO;
import com.mobabuild.api_build.service.IFavoriteBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteBuildServiceImpl implements IFavoriteBuildService {

    @Autowired
    private IFavoriteBuildDAO favoriteBuildDAO;

    @Override
    public List<FavoriteBuild> findAll() {
        return favoriteBuildDAO.findAll();
    }

    @Override
    public Optional<FavoriteBuild> findById(Long id) {
        return favoriteBuildDAO.findById(id);
    }

    @Override
    public void save(FavoriteBuild favoriteBuild) {
        favoriteBuildDAO.save(favoriteBuild);
    }

    @Override
    public void deleteById(Long id) {
        favoriteBuildDAO.deleteById(id);
    }
}
