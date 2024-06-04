package com.mobabuild.api_build.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rune_set" )
public class RuneSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "main_rune")
    private String main_rune;

    @Column(name = "main_sub_rune")
    private String main_sub_rune;

    @Column(name = "secondary_rune")
    private String secondary_rune;

    @Column(name = "secondary_sub_rune")
    private String secondary_sub_rune;

    @Column(name = "additional_advantages")
    private String additional_advantages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build")
    private Build build;
}
