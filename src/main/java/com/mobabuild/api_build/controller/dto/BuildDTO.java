package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.entities.RuneSet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildDTO {

    private Long id;
    private String buildName;
    private UserDTO user;
    private ChampionsDTO champions;
    private List<SpellSetDTO> spellSets = new ArrayList<>();
    private List<ObjectSetDTO> objectSet = new ArrayList<>();
    private List<RuneSetDTO> runeSet = new ArrayList<>();
}
