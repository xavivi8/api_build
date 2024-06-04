package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Object;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectSetDTO {

    private Long id;
    private String name;
    private BuildDTO build;
    private List<ObjectDTO> objects = new ArrayList<>();
}
