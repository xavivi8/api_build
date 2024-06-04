package com.mobabuild.api_build.controller.comand;

import com.mobabuild.api_build.entities.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildComand {
    private Long id;
    private String buildName;
    private UserComand user;
    private Champions champions;
    private List<SpellSetComand> spellSets = new ArrayList<>();
    private List<ObjectSetComand> objectSet = new ArrayList<>();
    private List<RuneSetComand> runeSet = new ArrayList<>();
}
