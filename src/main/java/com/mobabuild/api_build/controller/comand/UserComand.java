package com.mobabuild.api_build.controller.comand;

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
public class UserComand {
    private Long id;
    private String email;
    private String user_name;
    private String pass;
    private byte[] image;
    //private List<Build> builds = new ArrayList<>(); // No cargar builds aquí
    private List<Authority> authorities;
}
