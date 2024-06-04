package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.utils.AuthorityName;

import java.util.Optional;

public interface IAuthorityDAO {

    Optional<Authority> findByName(AuthorityName name);

    Iterable<Authority> findAll();
}
