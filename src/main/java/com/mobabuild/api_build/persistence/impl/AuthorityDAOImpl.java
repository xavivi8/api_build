package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.persistence.IAuthorityDAO;
import com.mobabuild.api_build.repository.AuthorityRepository;
import com.mobabuild.api_build.utils.AuthorityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorityDAOImpl implements IAuthorityDAO {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public Optional<Authority> findByName(AuthorityName name) {
        return authorityRepository.findByName(name);
    }

    @Override
    public Iterable<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
