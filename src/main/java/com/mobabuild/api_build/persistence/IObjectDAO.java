package com.mobabuild.api_build.persistence;


import com.mobabuild.api_build.entities.Object;

import java.util.List;
import java.util.Optional;

public interface IObjectDAO {

    List<Object> findAll();

    Optional<Object> findById(Long id);

    void save(Object object);

    void deleteById(Long id);

    int setObject(String name);

    boolean updateObjectName(Long id, String name);
}
