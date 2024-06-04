package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.ObjectSet;
import jakarta.persistence.Column;
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
public class ObjectDTO {
    private Long id;
    private String name;
    private byte[] image;
    private List<ObjectSet> objectSets = new ArrayList<>();
}
