package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteBuildDTO {

    private Long id;
    private UserDTO user;
    private List<Long> builds = new ArrayList<>();
}
