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
@Table(name = "spell_set" )
public class SpellSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build")
    @JsonIgnore
    private Build build;

    @ManyToMany
    @JoinTable(
            name = "spell_set_spell",
            joinColumns = @JoinColumn(name = "spell_set_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    @JsonIgnore
    private List<Spell> spells = new ArrayList<>();
}
