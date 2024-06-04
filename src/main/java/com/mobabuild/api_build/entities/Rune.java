package com.mobabuild.api_build.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rune" )
public class Rune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rowType")
    private String rowType;

    @Column(name = "group_name")
    private String group_name;

    @Column(name = "description")
    private String description;

    @Column(name = "long_description")
    private String long_description;

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private Blob image;
}
