package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.ObjectComand;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.persistence.IObjectDAO;
import com.mobabuild.api_build.service.IObjectService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectServiceImpl implements IObjectService {

    @Autowired
    private IObjectDAO objectDAO;

    @Override
    public List<Object> findAll() {
        return objectDAO.findAll();
    }

    @Override
    public Optional<Object> findById(Long id) {
        return objectDAO.findById(id);
    }

    @Override
    public ObjectDTO save(ObjectComand objectComand) {
        Object object = convertToObject(objectComand);

        objectDAO.save(object);

        return createObjectDTO(object);
    }

    @Override
    public void deleteById(Long id) {
        objectDAO.deleteById(id);
    }

    @Override
    public int setObject(String name) {
        return objectDAO.setObject(name);
    }

    @Override
    public boolean updateObjectName(Long id, String name) {
        return objectDAO.updateObjectName(id, name);
    }

    private Object convertToObject(ObjectComand objectComand){
        return Object.builder()
                .id(objectComand.getId())
                .name(objectComand.getName())
                .image(BlobUtils.bytesToBlob(objectComand.getImage()))
                .build();
    }

    private ObjectDTO createObjectDTO(Object object){
        return ObjectDTO.builder()
                .id(object.getId())
                .name(object.getName())
                .image(BlobUtils.blobToBytes(object.getImage()))
                .build();
    }
}
