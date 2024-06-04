package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.RuneComand;
import com.mobabuild.api_build.controller.dto.RuneDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.persistence.IRuneDAO;
import com.mobabuild.api_build.service.IRuneService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuneServiceImpl implements IRuneService {

    @Autowired
    private IRuneDAO runeDAO;

    @Override
    public List<Rune> findAll() {
        return runeDAO.findAll();
    }

    @Override
    public Optional<Rune> findById(Long id) {
        return runeDAO.findById(id);
    }

    @Override
    public void save(Rune rune) {
        runeDAO.save(rune);
    }

    @Override
    public void deleteById(Long id) {
        runeDAO.deleteById(id);
    }

    @Override
    public int insertRune(String name, String row, String group_name, String description, String long_description, byte[] image) {
        return runeDAO.insertRune(name, row, group_name, description, long_description, image);
    }

    @Override
    public int insertRuneWithoutImage(String name, String row, String group_name, String description, String long_description) {
        return runeDAO.insertRuneWithoutImage(name, row, group_name, description, long_description);
    }

    @Override
    public RuneDTO update(RuneComand runeComand) {
        Optional<Rune> runeOptional = runeDAO.findById(runeComand.getId());
        if(runeOptional.isPresent()){
            Rune rune = Rune.builder()
                    .id(runeComand.getId())
                    .name(runeComand.getName())
                    .rowType(runeComand.getRowType())
                    .group_name(runeComand.getGroup_name())
                    .description(runeComand.getDescription())
                    .long_description(runeComand.getLong_description())
                    .image(BlobUtils.bytesToBlob(runeComand.getImage()))
                    .build();

            runeDAO.save(rune);

            RuneDTO runeDTO = RuneDTO.builder()
                    .id(runeComand.getId())
                    .name(runeComand.getName())
                    .rowType(runeComand.getRowType())
                    .group_name(runeComand.getGroup_name())
                    .description(runeComand.getDescription())
                    .long_description(runeComand.getLong_description())
                    .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(runeComand.getImage())))
                    .build();

            return runeDTO;
        }else {
            throw new RuntimeException("Champion not found");
        }
    }
}
