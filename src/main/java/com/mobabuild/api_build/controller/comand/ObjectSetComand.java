package com.mobabuild.api_build.controller.comand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Object;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectSetComand {
    private Long id;
    private String name;
    private BuildComand builds;
    private List<ObjectComand> objects = new ArrayList<>();
}
