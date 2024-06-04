package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.ObjectComand;
import com.mobabuild.api_build.controller.dto.BuildDTO;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/object")
public class ObjectController {

    @Autowired
    private IObjectService objectService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Object> objectOptional = objectService.findById(id);

        if(objectOptional.isPresent()){
            Object object = objectOptional.get();

            ObjectDTO objectDTO = ObjectDTO.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .objectSets(object.getObjectSets())
                    .build();

            return  ResponseEntity.ok(objectDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Object> objects = objectService.findAll();

        if(!objects.isEmpty()){
            List<ObjectDTO> objectDTOs = new ArrayList<>();
            for(Object object : objects){
                ObjectDTO objectDTO = ObjectDTO.builder()
                        .id(object.getId())
                        .name(object.getName())
                        .objectSets(object.getObjectSets())
                        .build();
                objectDTOs.add(objectDTO);
            }
            return ResponseEntity.ok(objectDTOs);
        }

        return  ResponseEntity.notFound().build();
    }


    @GetMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            objectService.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }



    @PostMapping("/create")
    public ResponseEntity<?> setObject(@RequestBody ObjectComand objectComand){
        try {

            ObjectDTO objectDTO = objectService.save(objectComand);

            return ResponseEntity.ok(objectDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de integridad de datos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al añadir el objeto: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateObjectName(@RequestBody ObjectComand objectComand) {
        try {

            ObjectDTO objectDTO = objectService.save(objectComand);

            return ResponseEntity.ok(objectDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
