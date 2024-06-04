package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.utils.AuthorityName;

import java.util.Optional;

public interface IAuthorityService {

    Optional<Authority> findByName(AuthorityName name);

    Iterable<Authority> findAll();

}
