package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.Rune;

import java.util.List;
import java.util.Optional;

public interface IRuneDAO {

    List<Rune> findAll();

    Optional<Rune> findById(Long id);

    void save(Rune rune);

    void deleteById(Long id);

    int insertRune(String name, String row, String group_name, String description, String long_description, byte[] image);

    int insertRuneWithoutImage(String name, String row, String group_name, String description, String long_description);
}
