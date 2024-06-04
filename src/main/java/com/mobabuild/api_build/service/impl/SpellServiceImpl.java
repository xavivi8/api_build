package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.persistence.ISpellDAO;
import com.mobabuild.api_build.service.ISpellService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpellServiceImpl implements ISpellService {

    @Autowired
    private ISpellDAO spellDAO;

    @Override
    public List<Spell> findAll() {
        return spellDAO.findAll();
    }

    @Override
    public Optional<Spell> findById(Long id) {
        return spellDAO.findById(id);
    }

    @Override
    public void save(Spell spell) {
        spellDAO.save(spell);
    }

    @Override
    public void deleteById(Long id) {
        spellDAO.deleteById(id);
    }

    @Override
    public SpellDTO update(SpellComand spellComand) {
        Optional<Spell> spellOptional = spellDAO.findById(spellComand.getId());
        if(spellOptional.isPresent()){
            Spell spell = Spell.builder()
                    .id(spellComand.getId())
                    .name(spellComand.getName())
                    .champion_level(spellComand.getChampion_level())
                    .game_mode(spellComand.getGame_mode())
                    .description(spellComand.getDescription())
                    .cooldown(spellComand.getCooldown())
                    .image(BlobUtils.bytesToBlob(spellComand.getImage()))
                    .build();

            spellDAO.save(spell);

            SpellDTO spellDTO = SpellDTO.builder()
                    .id(spellComand.getId())
                    .name(spellComand.getName())
                    .champion_level(spellComand.getChampion_level())
                    .game_mode(spellComand.getGame_mode())
                    .description(spellComand.getDescription())
                    .cooldown(spellComand.getCooldown())
                    .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(spellComand.getImage())))
                    .build();

            return spellDTO;
        }else {
            throw new RuntimeException("Champion not found");
        }
    }
}
