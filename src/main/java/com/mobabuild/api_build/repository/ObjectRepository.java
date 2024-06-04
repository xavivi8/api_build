package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Object;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ObjectRepository extends CrudRepository<Object, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO object (name) VALUES (:name)", nativeQuery = true)
    int setObject(String name); // Cambio de tipo de retorno a Object

    @Modifying
    @Transactional
    @Query("UPDATE Object o SET o.name = :name WHERE o.id = :id")
    int updateObjectName(Long id, String name);
}
