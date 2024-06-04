package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.ObjectComand;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.entities.Object;

import java.util.List;
import java.util.Optional;

public interface IObjectService {

    List<Object> findAll();

    Optional<Object> findById(Long id);

    ObjectDTO save(ObjectComand objectComand);

    void deleteById(Long id);

    int setObject(String name);

    boolean updateObjectName(Long id, String name);
}
