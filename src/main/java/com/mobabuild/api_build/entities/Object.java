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
@Table(name = "object" )
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private Blob image;

    @ManyToMany(mappedBy = "objects", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JsonIgnore
    private List<ObjectSet> objectSets = new ArrayList<>();
}
