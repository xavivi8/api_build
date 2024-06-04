package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.SpellSet;
import lombok.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpellDTO {

    private Long id;
    private String name;
    private int champion_level;
    private String game_mode;
    private String description;
    private String cooldown;
    private byte[] image;
    private List<SpellSetDTO> spellSets = new ArrayList<>();
}
