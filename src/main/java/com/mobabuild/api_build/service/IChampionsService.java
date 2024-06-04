package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.ChampionsDTO;
import com.mobabuild.api_build.entities.Champions;

import java.util.List;
import java.util.Optional;

public interface IChampionsService {

    List<Champions> findAll();

    Optional<Champions> findById(Long id);

    ChampionsDTO save(ChampionComand championComand);

    void deleteById(Long id);

    int setChampion(String name);

    ChampionsDTO updateChampion(ChampionComand championComand);
}
