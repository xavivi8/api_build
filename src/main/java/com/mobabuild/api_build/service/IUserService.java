package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.UserComand;
import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);

    User findByUserAndPass(String email, String pass);

    UserDTO updateUser(UserComand newUser);

    User addUserWithoutImage(String email, String user_name, String pass);
}
