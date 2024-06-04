package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Spell;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpellSetDTO {

    private Long id;
    private String name;
    private BuildDTO build;
    private List<SpellDTO> spells = new ArrayList<>();
}
