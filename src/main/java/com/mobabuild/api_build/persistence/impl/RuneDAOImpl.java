package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.persistence.IRuneDAO;
import com.mobabuild.api_build.repository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RuneDAOImpl implements IRuneDAO {

    @Autowired
    private RuneRepository runeRepository;

    @Override
    public List<Rune> findAll() {
        return (List<Rune>) runeRepository.findAll();
    }

    @Override
    public Optional<Rune> findById(Long id) {
        return runeRepository.findById(id);
    }

    @Override
    public void save(Rune rune) {
        runeRepository.save(rune);
    }

    @Override
    public void deleteById(Long id) {
        runeRepository.deleteById(id);
    }

    @Override
    public int insertRune(String name, String row, String group_name, String description, String long_description, byte[] image) {
        return runeRepository.insertRune(name, row, group_name, description, long_description, image);
    }

    @Override
    public int insertRuneWithoutImage(String name, String row, String group_name, String description, String long_description) {
        return runeRepository.insertRuneWithoutImage(name, row, group_name, description, long_description);
    }
}
