package com.mobabuild.api_build.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite_build" )
public class FavoriteBuild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Nombre de la columna que actúa como clave foránea
    @JsonIgnore
    private User user;

    @ElementCollection
    @CollectionTable(name = "favorite_builds", joinColumns = @JoinColumn(name = "favorite_build_id"))
    @Column(name = "build_id")
    private List<Long> builds = new ArrayList<>();
}
