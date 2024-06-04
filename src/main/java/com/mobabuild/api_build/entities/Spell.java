package com.mobabuild.api_build.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spell" )
public class Spell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "champion_level")
    private int champion_level;

    @Column(name = "game_mode")
    private String game_mode;

    @Column(name = "description")
    private String description;

    @Column(name = "cooldown")
    private String cooldown;

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private Blob image;

    @ManyToMany(mappedBy = "spells")
    @JsonIgnore
    private List<SpellSet> spellSets = new ArrayList<>();
}
