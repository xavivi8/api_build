package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.persistence.IBuildDAO;
import com.mobabuild.api_build.persistence.IChampionsDAO;
import com.mobabuild.api_build.service.IChampionsService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionsServiceImpl implements IChampionsService {

    @Autowired
    private IChampionsDAO championsDAO;

    @Override
    public List<Champions> findAll() {
        return championsDAO.findAll();
    }

    @Override
    public Optional<Champions> findById(Long id) {
        return championsDAO.findById(id);
    }

    @Override
    public ChampionsDTO save(ChampionComand championComand) {
        Champions champions = convertToChampion(championComand);

        championsDAO.save(champions);

        return createChampionDTO(champions);
    }

    @Override
    public void deleteById(Long id) {
        championsDAO.deleteById(id);
    }

    @Override
    public int setChampion(String name) {
        return championsDAO.setChampion(name);
    }

    @Override
    public ChampionsDTO updateChampion(ChampionComand championComand) {
        Optional<Champions> championsOptional = championsDAO.findById(championComand.getId());
        if (championsOptional.isPresent()) {
            Champions championsExist = championsOptional.get();
            championsExist.setName(championComand.getName());

            // Verificar si la lista de builds es nula
            List<Build> newBuilds = championComand.getBuilds();
            if (newBuilds != null) {
                // Actualizar la colección de builds
                championsExist.getBuilds().clear();
                for (Build build : newBuilds) {
                    build.setChampions(championsExist);
                    championsExist.getBuilds().add(build);
                }
            }

            ChampionsDTO championsDTO = ChampionsDTO.builder()
                    .id(championsExist.getId())
                    .name(championsExist.getName())
                    .builds(null) // Asumiendo que no necesitas devolver la lista de builds en el DTO
                    .build();

            championsDAO.save(championsExist);
            return championsDTO;
        } else {
            throw new RuntimeException("Champion not found");
        }
    }

    private Champions convertToChampion(ChampionComand championComand){
        return Champions.builder()
                .id(championComand.getId())
                .name(championComand.getName())
                .image(BlobUtils.bytesToBlob(championComand.getImage()))
                .build();
    }

    private ChampionsDTO createChampionDTO(Champions champions){
        return ChampionsDTO.builder()
                .id(champions.getId())
                .name(champions.getName())
                .image(BlobUtils.blobToBytes(champions.getImage()))
                .build();
    }
}
