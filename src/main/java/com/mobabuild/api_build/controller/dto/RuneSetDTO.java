package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Build;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuneSetDTO {

    private Long id;
    private String name;
    private String main_rune;
    private String main_sub_rune;
    private String secondary_rune;
    private String secondary_sub_rune;
    private String additional_advantages;
    private BuildDTO build;
}
