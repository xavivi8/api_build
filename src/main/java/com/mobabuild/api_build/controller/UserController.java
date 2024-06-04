package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.UserComand;
import com.mobabuild.api_build.controller.comand.UserLoginComand;
import com.mobabuild.api_build.controller.dto.AuthorityDTO;
import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.controller.request.AddUserRequest;
import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.service.IAuthorityService;
import com.mobabuild.api_build.service.IUserService;
import com.mobabuild.api_build.utils.AuthorityName;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            // Inicializar la colección authorities antes de acceder a ella
            Hibernate.initialize(user.getAuthorities());

            // Convertir la lista de Authority a AuthorityDTO
            List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(BlobUtils.blobToBytes(user.getImage()))
                    .authorities(authorityDTOs)
                    .build();
            return  ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserComand userComand) {
        try {
            UserDTO updatedUser = userService.updateUser(userComand);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> findByUserAndPass(@RequestBody UserLoginComand userLoginComand){
        try {
            User user = userService.findByUserAndPass(userLoginComand.getEmail(), userLoginComand.getPass());

            List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(BlobUtils.blobToBytes(user.getImage()))
                    .authorities(authorityDTOs)
                    .build();

            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserComand userComand){
        try {
            Optional<Authority> authorityReadOptional = authorityService.findByName(AuthorityName.READ);
            Optional<Authority> authorityWriteOptional = authorityService.findByName(AuthorityName.WRITE);
            if (authorityReadOptional.isPresent() && authorityWriteOptional.isPresent()){
                List<Authority> authorities = new ArrayList<>();
                authorities.add(authorityReadOptional.get());
                authorities.add(authorityWriteOptional.get());

                User newUser = User.builder()
                        .email(userComand.getEmail())
                        .user_name(userComand.getUser_name())
                        .pass(userComand.getPass())
                        .authorities(authorities)
                        .build();

                User user = userService.save(newUser);

                List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                        .map(authority -> AuthorityDTO.builder()
                                .id(authority.getId())
                                .name(authority.getName())
                                .build())
                        .collect(Collectors.toList());

                UserDTO userDTO = UserDTO.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .user_name(user.getUser_name())
                        .pass(user.getPass())
                        .image(BlobUtils.blobToBytes(user.getImage()))
                        .authorities(authorityDTOs)
                        .build();
                return ResponseEntity.ok(userDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para añadir un usuario sin imagen usando GET
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUserWithoutImage(@Validated @RequestBody AddUserRequest addUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return  ResponseEntity.notFound().build();
        }
        // Extraer los datos del objeto de solicitud
        String email = addUserRequest.getEmail();
        String userName = addUserRequest.getUserName();
        String pass = addUserRequest.getPass();
        List<String> authorityNames = addUserRequest.getAuthorityNames();

        // Crea una lista para almacenar las autoridades asociadas al nuevo usuario
        List<Authority> authorities = new ArrayList<>();

        // Busca las autoridades por nombre y agrégalas a la lista
        for (String authorityName : authorityNames) {
            AuthorityName authorityNameEnum;
            try {
                authorityNameEnum = AuthorityName.valueOf(authorityName);
            } catch (IllegalArgumentException e) {
                // Maneja el caso donde el nombre de la autoridad no es válido
                return ResponseEntity.badRequest().build();
            }

            Optional<Authority> authorityOptional = authorityService.findByName(authorityNameEnum);
            if (authorityOptional.isEmpty()) {
                // Maneja el caso donde no se encuentra ninguna autoridad con el nombre proporcionado
                return ResponseEntity.notFound().build();
            }

            authorities.add(authorityOptional.get());
        }

        // Crea el nuevo usuario
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUser_name(userName);
        newUser.setPass(pass);
        // Asigna las autoridades al nuevo usuario
        newUser.setAuthorities(authorities);

        // Guarda el nuevo usuario en la base de datos
        newUser = userService.save(newUser);

        List<AuthorityDTO> authorityDTOs = newUser.getAuthorities().stream()
                .map(authority -> AuthorityDTO.builder()
                        .id(authority.getId())
                        .name(authority.getName())
                        .build())
                .collect(Collectors.toList());

        // Convierte el nuevo usuario a DTO para enviarlo como respuesta
        UserDTO userDTO = UserDTO.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .user_name(newUser.getUser_name())
                .pass(newUser.getPass())
                .image(BlobUtils.blobToBytes(newUser.getImage()))
                .authorities(authorityDTOs)
                .build();

        // Devuelve el nuevo usuario creado como respuesta
        return ResponseEntity.ok(userDTO);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();

        List<UserDTO> userDTOs = users.stream().map(user -> {
            // Inicializar la colección authorities antes de acceder a ella
            Hibernate.initialize(user.getAuthorities());

            List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            return UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(BlobUtils.blobToBytes(user.getImage()))
                    .authorities(authorityDTOs)
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }
}
