package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.RuneSet;

import java.util.List;
import java.util.Optional;

public interface IRuneSetDAO {

    List<RuneSet> findAll();

    Optional<RuneSet> findById(Long id);

    void save(RuneSet runeSet);

    void deleteById(Long id);

}
