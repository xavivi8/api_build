package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampionsDTO {

    private Long id;
    private String name;
    private byte[] image;
    private List<BuildDTO> builds;
}
