package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.FavoriteBuild;
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
public class UserDTO {
    private Long id;
    private String email;
    private String user_name;
    private String pass;
    private byte[] image;
    private List<BuildDTO> builds = new ArrayList<>(); // No cargar builds aquí
    private FavoriteBuildDTO favoriteBuild; // No cargar favoriteBuild aquí
    private List<AuthorityDTO> authorities; // No cargar authorities aquí
}