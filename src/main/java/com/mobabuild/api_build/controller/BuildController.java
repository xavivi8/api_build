package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.*;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.*;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IBuildService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/build")
public class BuildController {

    @Autowired
    private IBuildService buildService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            buildService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la build: " + e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BuildDTO>> findAll(){
        try {
            List<Build> buildList = buildService.findAll();

            List<BuildDTO> buildDTOList = buildList.stream()
                    .map(this::createBuildDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(buildDTOList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

     @GetMapping("/findById/{id}")
     public ResponseEntity<BuildDTO> findById(@PathVariable Long id){
         try {
             BuildDTO buildDTO = buildService.findById(id);

             return ResponseEntity.ok(buildDTO);
         } catch (Exception e) {
             return ResponseEntity.notFound().build();
         }
     }

    @PostMapping("/findByChampion")
    public ResponseEntity<List<BuildDTO>> findByChampion(@RequestBody ChampionComand championComand) {
        try {
            List<BuildDTO> builds = buildService.findByChampionsId(championComand);
            return ResponseEntity.ok(builds);
        } catch (Exception e) {
            // Manejo de la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/findByUser")
    public ResponseEntity<List<BuildDTO>> findByUser(@RequestBody UserComand userComand) {
        try {
            List<BuildDTO> builds = buildService.findByUserId(userComand);
            return ResponseEntity.ok(builds);
        } catch (Exception e) {
            // Manejo de la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BuildComand buildComand){
        try {

            BuildDTO buildDTO = buildService.save(buildComand);

            return ResponseEntity.ok(buildDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de integridad de datos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al añadir la build: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BuildDTO> update(@RequestBody BuildComand buildComand) {
        try {

            BuildDTO updatedUser = buildService.save(buildComand);

            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
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

        return Build.builder()
                .buildName(buildComand.getBuildName())
                .user(user)
                .champions(buildComand.getChampions())
                .spellSets(spellSets)
                .objectSet(objectSets)
                .runeSet(runeSets)
                .build();
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
                .build();
    }

    private BuildDTO convertToBuildDTO(BuildComand buildComand){

        UserDTO userDTO = convertToUserDTO(buildComand.getUser());

        ChampionsDTO championsDTO = ChampionsDTO.builder()
                .id(buildComand.getChampions().getId())
                .name(buildComand.getChampions().getName())
                .image(BlobUtils.blobToBytes(buildComand.getChampions().getImage()))
                .build();

        List<SpellSetDTO> spellSetsDTO = buildComand.getSpellSets().stream()
                .map(spellSet -> SpellSetDTO.builder()
                        .id(spellSet.getId())
                        .name(spellSet.getName())
                        .spells(spellSet.getSpells().stream()
                                .map(this::convertToSpellDTO)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<ObjectSetDTO> objectSetsDTO = buildComand.getObjectSet().stream()
                .map(objectSet -> ObjectSetDTO.builder()
                        .id(objectSet.getId())
                        .name(objectSet.getName())
                        .objects(objectSet.getObjects().stream()
                                .map(this::convertToObjectDTO)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<RuneSetDTO> runeSetsDTO = buildComand.getRuneSet().stream()
                .map(runeSet -> RuneSetDTO.builder()
                        .id(runeSet.getId())
                        .name(runeSet.getName())
                        .main_rune(runeSet.getMain_rune())
                        .main_sub_rune(runeSet.getMain_sub_rune())
                        .secondary_rune(runeSet.getSecondary_rune())
                        .secondary_sub_rune(runeSet.getSecondary_sub_rune())
                        .additional_advantages(runeSet.getAdditional_advantages())
                        .build())
                .collect(Collectors.toList());

        return BuildDTO.builder()
                .buildName(buildComand.getBuildName())
                .user(userDTO)
                .champions(championsDTO)
                .spellSets(spellSetsDTO)
                .objectSet(objectSetsDTO)
                .runeSet(runeSetsDTO)
                .build();
    }

    private UserDTO convertToUserDTO(UserComand userComand){
        return  UserDTO.builder()
                .id(userComand.getId())
                .email(userComand.getEmail())
                .user_name(userComand.getUser_name())
                .pass(userComand.getPass())
                .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(userComand.getImage())))
                .build();
    }

    private SpellDTO convertToSpellDTO(SpellComand spellComand) {
        return SpellDTO.builder()
                .id(spellComand.getId())
                .name(spellComand.getName())
                .champion_level(spellComand.getChampion_level())
                .game_mode(spellComand.getGame_mode())
                .description(spellComand.getDescription())
                .cooldown(spellComand.getCooldown())
                .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(spellComand.getImage())))
                .build();
    }

    private ObjectDTO convertToObjectDTO(ObjectComand objectComand) {
        return ObjectDTO.builder()
                .id(objectComand.getId())
                .name(objectComand.getName())
                .build();
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
                .image(BlobUtils.blobToBytes(user.getImage()))
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
}
