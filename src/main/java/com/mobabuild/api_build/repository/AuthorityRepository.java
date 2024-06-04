package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.utils.AuthorityName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>{

    Optional<Authority> findByName(AuthorityName name);

    @Override
    Iterable<Authority> findAll();
}
