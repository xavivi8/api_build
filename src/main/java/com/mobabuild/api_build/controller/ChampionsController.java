package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IChampionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/champions")
public class ChampionsController {

    @Autowired
    private IChampionsService championsService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Champions> championsOptional = championsService.findById(id);

        if(championsOptional.isPresent()){
            Champions champions = championsOptional.get();

            // Convierte los builds a BuildDTO
            List<BuildDTO> buildDTOs = champions.getBuilds().stream()
                    .map(build -> BuildDTO.builder()
                            .id(build.getId())
                            .buildName(build.getBuildName())
                            .user(UserDTO.builder() // Conversión directa de User a UserDTO
                                    .id(build.getUser().getId())
                                    .user_name(build.getUser().getUser_name())
                                    .email(build.getUser().getEmail())
                                    .build())
                            .spellSets(build.getSpellSets().stream() // Conversión directa de SpellSet a SpellSetDTO
                                    .map(spellSet -> SpellSetDTO.builder()
                                            .id(spellSet.getId())
                                            .name(spellSet.getName())
                                            .build())
                                    .collect(Collectors.toList()))
                            .objectSet(build.getObjectSet().stream() // Conversión directa de ObjectSet a ObjectSetDTO
                                    .map(objectSet -> ObjectSetDTO.builder()
                                            .id(objectSet.getId())
                                            .name(objectSet.getName())
                                            // Agrega más campos según sea necesario
                                            .build())
                                    .collect(Collectors.toList()))
                            .runeSet(null)
                            .build())
                    .collect(Collectors.toList());

            ChampionsDTO championsDTO = ChampionsDTO.builder()
                    .id(champions.getId())
                    .name(champions.getName())
                    .builds(buildDTOs)
                    .build();

            return  ResponseEntity.ok(championsDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Champions> champions = championsService.findAll();

        if(!champions.isEmpty()){
            List<ChampionsDTO> championsDTOS = new ArrayList<>();
            for(Champions champion : champions){
                // Convierte los builds a BuildDTO
                List<BuildDTO> buildDTOs = champion.getBuilds().stream()
                        .map(build -> BuildDTO.builder()
                                .id(build.getId())
                                .buildName(build.getBuildName())
                                .user(UserDTO.builder() // Conversión directa de User a UserDTO
                                        .id(build.getUser().getId())
                                        .user_name(build.getUser().getUser_name())
                                        .email(build.getUser().getEmail())
                                        .build())
                                .spellSets(build.getSpellSets().stream() // Conversión directa de SpellSet a SpellSetDTO
                                        .map(spellSet -> SpellSetDTO.builder()
                                                .id(spellSet.getId())
                                                .name(spellSet.getName())
                                                .build())
                                        .collect(Collectors.toList()))
                                .objectSet(build.getObjectSet().stream() // Conversión directa de ObjectSet a ObjectSetDTO
                                        .map(objectSet -> ObjectSetDTO.builder()
                                                .id(objectSet.getId())
                                                .name(objectSet.getName())
                                                // Agrega más campos según sea necesario
                                                .build())
                                        .collect(Collectors.toList()))
                                .runeSet(null)
                                .build())
                        .collect(Collectors.toList());

                ChampionsDTO championsDTO = ChampionsDTO.builder()
                        .id(champion.getId())
                        .name(champion.getName())
                        .builds(buildDTOs)
                        .build();
                championsDTOS.add(championsDTO);
            }
            return ResponseEntity.ok(championsDTOS);
        }

        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            championsService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ChampionComand championComand){
        try {

            ChampionsDTO championsDTO = championsService.save(championComand);

            return ResponseEntity.ok(championsDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de integridad de datos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al añadir el campeon: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateChampion(@RequestBody ChampionComand championComand){
        try {
            ChampionsDTO championsDTO = championsService.save(championComand);
            return ResponseEntity.ok(championsDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
