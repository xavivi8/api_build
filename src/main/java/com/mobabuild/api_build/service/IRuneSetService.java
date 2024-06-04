package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.RuneSet;

import java.util.List;
import java.util.Optional;

public interface IRuneSetService {

    List<RuneSet> findAll();

    Optional<RuneSet> findById(Long id);

    void save(RuneSet runeSet);

    void deleteById(Long id);
}
