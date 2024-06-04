package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.*;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.*;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.persistence.IBuildDAO;
import com.mobabuild.api_build.service.IBuildService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildServiceImpl implements IBuildService {

    @Autowired
    private IBuildDAO buildDAO;

    @Override
    public List<Build> findAll() {
        return buildDAO.findAll();
    }

    @Override
    public BuildDTO findById(Long id) {
        Optional<Build> buildOptional = buildDAO.findById(id);

        if (buildOptional.isPresent()) {
            BuildDTO buildDTO = createBuildDTO(buildOptional.get());
            return buildDTO;
        } else {
            return null;
        }
    }



    @Override
    public BuildDTO save(BuildComand buildComand) {
        Build build = convertToBuild(buildComand);

        // Establecer relaciones bidireccionales para objectSet
        for (ObjectSet objectSet : build.getObjectSet()) {
            objectSet.setBuild(build);
        }

        // Establecer relaciones bidireccionales para spellSets
        for (SpellSet spellSet : build.getSpellSets()) {
            spellSet.setBuild(build);
        }

        // Establecer relaciones bidireccionales para runeSet
        for (RuneSet runeSet : build.getRuneSet()) {
            runeSet.setBuild(build);
        }

        // Guardar la entidad build, lo que en cascada guarda las entidades relacionadas
        buildDAO.save(build);

        BuildDTO buildDTO = createBuildDTO(build);
        return  buildDTO;
    }

    @Override
    public void deleteById(Long id) {
        buildDAO.deleteById(id);
    }

    @Override
    public List<BuildDTO> findByChampionsId(ChampionComand championComand) {

        List<BuildDTO> buildDTOList = buildDAO.findByChampionsId(championComand.getId()).stream()
                .map(this::createBuildDTO)
                .collect(Collectors.toList());

        return buildDTOList;
    }

    @Override
    public List<BuildDTO> findByUserId(UserComand userComand) {
        List<BuildDTO> buildDTOList = buildDAO.findByUserId(userComand.getId()).stream()
                .map(this::createBuildDTO)
                .collect(Collectors.toList());
        return buildDTOList;
    }

    private BuildDTO createBuildDTO(Build build){

        UserDTO userDTO = createUserDTO(build.getUser());

        ChampionsDTO championsDTO = ChampionsDTO.builder()
                .id(build.getChampions().getId())
                .name(build.getChampions().getName())
                .image(BlobUtils.blobToBytes(build.getChampions().getImage()))
                .build();

        List<SpellSetDTO> spellSetsDTO = build.getSpellSets().stream()
                .map(spellSet -> SpellSetDTO.builder()
                        .id(spellSet.getId())
                        .name(spellSet.getName())
                        //.build(createBuildDTO(spellSet.getBuild()))
                        .spells(spellSet.getSpells().stream()
                                .map(this::createSpellDTO)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<ObjectSetDTO> objectSetsDTO = build.getObjectSet().stream()
                .map(objectSet -> ObjectSetDTO.builder()
                        .id(objectSet.getId())
                        .name(objectSet.getName())
                        //.build(createBuildDTO(objectSet.getBuild()))
                        .objects(objectSet.getObjects().stream()
                                .map(this::createObjectDTO)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<RuneSetDTO> runeSetsDTO = build.getRuneSet().stream()
                .map(runeSet -> RuneSetDTO.builder()
                        .id(runeSet.getId())
                        .name(runeSet.getName())
                        .main_rune(runeSet.getMain_rune())
                        .main_sub_rune(runeSet.getMain_sub_rune())
                        .secondary_rune(runeSet.getSecondary_rune())
                        .secondary_sub_rune(runeSet.getSecondary_sub_rune())
                        .additional_advantages(runeSet.getAdditional_advantages())
                        //.build(createBuildDTO(runeSet.getBuild()))
                        .build())
                .collect(Collectors.toList());

        return BuildDTO.builder()
                .id(build.getId())
                .buildName(build.getBuildName())
                .user(userDTO)
                .champions(championsDTO)
                .spellSets(spellSetsDTO)
                .objectSet(objectSetsDTO)
                .runeSet(runeSetsDTO)
                .build();
    }

    private UserDTO createUserDTO(User user){

        return  UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .user_name(user.getUser_name())
                .pass(user.getPass())
                .build();
    }

    private FavoriteBuildDTO createFavoriteBuildDTO(FavoriteBuild favoriteBuild){

        UserDTO userDTO = createUserDTO(favoriteBuild.getUser());


        return FavoriteBuildDTO.builder()
                .id(favoriteBuild.getId())
                .user(userDTO)
                .builds(favoriteBuild.getBuilds())
                .build();
    }

    private SpellDTO createSpellDTO(Spell spell) {
        return SpellDTO.builder()
                .id(spell.getId())
                .name(spell.getName())
                .champion_level(spell.getChampion_level())
                .game_mode(spell.getGame_mode())
                .description(spell.getDescription())
                .cooldown(spell.getCooldown())
                .image(BlobUtils.blobToBytes(spell.getImage()))
                .build();
    }

    private ObjectDTO createObjectDTO(Object object) {
        return ObjectDTO.builder()
                .id(object.getId())
                .name(object.getName())
                .image(BlobUtils.blobToBytes(object.getImage()))
                .build();
    }

    private Build convertToBuild(BuildComand buildComand){

        User user = convertToUser(buildComand.getUser());

        List<SpellSet> spellSets = buildComand.getSpellSets().stream()
                .map(spellSet -> SpellSet.builder()
                        .id(spellSet.getId())
                        .name(spellSet.getName())
                        .spells(spellSet.getSpells().stream()
                                .map(this::convertToSpell)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<ObjectSet> objectSets = buildComand.getObjectSet().stream()
                .map(objectSet -> ObjectSet.builder()
                        .id(objectSet.getId())
                        .name(objectSet.getName())
                        .objects(objectSet.getObjects().stream()
                                .map(this::convertToObject)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<RuneSet> runeSets = buildComand.getRuneSet().stream()
                .map(runeSet -> RuneSet.builder()
                        .id(runeSet.getId())
                        .name(runeSet.getName())
                        .main_rune(runeSet.getMain_rune())
                        .main_sub_rune(runeSet.getMain_sub_rune())
                        .secondary_rune(runeSet.getSecondary_rune())
                        .secondary_sub_rune(runeSet.getSecondary_sub_rune())
                        .additional_advantages(runeSet.getAdditional_advantages())
                        .build())
                .collect(Collectors.toList());

        if(buildComand.getId() != null){
            return Build.builder()
                    .id(buildComand.getId())
                    .buildName(buildComand.getBuildName())
                    .user(user)
                    .champions(buildComand.getChampions())
                    .spellSets(spellSets)
                    .objectSet(objectSets)
                    .runeSet(runeSets)
                    .build();
        } else{
            return Build.builder()
                    .buildName(buildComand.getBuildName())
                    .user(user)
                    .champions(buildComand.getChampions())
                    .spellSets(spellSets)
                    .objectSet(objectSets)
                    .runeSet(runeSets)
                    .build();
        }
    }

    private User convertToUser(UserComand userComand){
        return  User.builder()
                .id(userComand.getId())
                .email(userComand.getEmail())
                .user_name(userComand.getUser_name())
                .pass(userComand.getPass())
                .image(BlobUtils.bytesToBlob(userComand.getImage()))
                .build();
    }

    private Spell convertToSpell(SpellComand spellComand) {
        return Spell.builder()
                .id(spellComand.getId())
                .name(spellComand.getName())
                .champion_level(spellComand.getChampion_level())
                .game_mode(spellComand.getGame_mode())
                .description(spellComand.getDescription())
                .cooldown(spellComand.getCooldown())
                .image(BlobUtils.bytesToBlob(spellComand.getImage()))
                .build();
    }

    private Object convertToObject(ObjectComand objectComand) {
        return Object.builder()
                .id(objectComand.getId())
                .name(objectComand.getName())
                .image(BlobUtils.bytesToBlob(objectComand.getImage()))
                .build();
    }
}
