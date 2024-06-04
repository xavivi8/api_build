package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.ChampionsDTO;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.service.ISpellService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spell")
public class SpellController {

    @Autowired
    private ISpellService spellService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Spell> spellOptional = spellService.findById(id);

        if(spellOptional.isPresent()){
            Spell spell = spellOptional.get();

            SpellDTO spellDTO = SpellDTO.builder()
                    .id(spell.getId())
                    .name(spell.getName())
                    .champion_level(spell.getChampion_level())
                    .game_mode(spell.getGame_mode())
                    .description(spell.getDescription())
                    .cooldown(spell.getCooldown())
                    .image(BlobUtils.blobToBytes(spell.getImage()))
                    //.spellSets(spell.getSpellSets())
                    .build();

            return  ResponseEntity.ok(spellDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Spell> spellList = spellService.findAll();

        if(!spellList.isEmpty()){
            List<SpellDTO> spellDTOList = new ArrayList<>();
            for(Spell spell : spellList){
                SpellDTO spellDTO = SpellDTO.builder()
                        .id(spell.getId())
                        .name(spell.getName())
                        .champion_level(spell.getChampion_level())
                        .game_mode(spell.getGame_mode())
                        .description(spell.getDescription())
                        .cooldown(spell.getCooldown())
                        .image(BlobUtils.blobToBytes(spell.getImage()))
                        //.spellSets(spell.getSpellSets())
                        .build();
                spellDTOList.add(spellDTO);
            }
            return ResponseEntity.ok(spellDTOList);
        }

        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            spellService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SpellComand spellComand){
        try {
            Spell newSpell = Spell.builder()
                    .name(spellComand.getName())
                    .champion_level(spellComand.getChampion_level())
                    .game_mode(spellComand.getGame_mode())
                    .description(spellComand.getDescription())
                    .cooldown(spellComand.getCooldown())
                    .image(BlobUtils.bytesToBlob(spellComand.getImage()))
                    .build();

            spellService.save(newSpell);

            SpellDTO spellDTO = SpellDTO.builder()
                    .name(spellComand.getName())
                    .champion_level(spellComand.getChampion_level())
                    .game_mode(spellComand.getGame_mode())
                    .description(spellComand.getDescription())
                    .cooldown(spellComand.getCooldown())
                    .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(spellComand.getImage())))
                    .build();

            return ResponseEntity.ok(spellDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SpellComand spellComand){
        try {
            SpellDTO spellDTO = spellService.update(spellComand);
            return ResponseEntity.ok(spellDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
