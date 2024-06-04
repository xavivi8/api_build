package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.RuneComand;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.RuneDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.service.IRuneService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rune")
public class RuneController {

    @Autowired
    private IRuneService runeService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Rune> runeOptional = runeService.findById(id);

        if (runeOptional.isPresent()) {
            Rune rune = runeOptional.get();

            RuneDTO runeDTO = RuneDTO.builder()
                    .id(rune.getId())
                    .name(rune.getName())
                    .rowType(rune.getRowType())
                    .group_name(rune.getGroup_name())
                    .description(rune.getDescription())
                    .long_description(rune.getLong_description())
                    .image(BlobUtils.blobToBytes(rune.getImage()))
                    .build();

            return ResponseEntity.ok(runeDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Rune> runeList = runeService.findAll();

        if (!runeList.isEmpty()) {
            List<RuneDTO> runeDTOList = new ArrayList<>();
            for (Rune rune : runeList) {
                RuneDTO runeDTO = RuneDTO.builder()
                        .id(rune.getId())
                        .name(rune.getName())
                        .rowType(rune.getRowType())
                        .group_name(rune.getGroup_name())
                        .description(rune.getDescription())
                        .long_description(rune.getLong_description())
                        .image(BlobUtils.blobToBytes(rune.getImage()))
                        .build();
                runeDTOList.add(runeDTO);
            }
            return ResponseEntity.ok(runeDTOList);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            runeService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> insertRune(@RequestBody RuneComand runeComand) {
        try {
            Rune rune = Rune.builder()
                    .name(runeComand.getName())
                    .rowType(runeComand.getRowType())
                    .group_name(runeComand.getGroup_name())
                    .description(runeComand.getDescription())
                    .long_description(runeComand.getLong_description())
                    .image(BlobUtils.bytesToBlob(runeComand.getImage()))
                    .build();

            runeService.save(rune);

            RuneDTO runeDTO = RuneDTO.builder()
                    .name(runeComand.getName())
                    .rowType(runeComand.getRowType())
                    .group_name(runeComand.getGroup_name())
                    .description(runeComand.getDescription())
                    .long_description(runeComand.getLong_description())
                    .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(runeComand.getImage())))
                    .build();

            return ResponseEntity.ok(runeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RuneComand runeComand){
        try {
            RuneDTO runeDTO = runeService.update(runeComand);
            return ResponseEntity.ok(runeDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
