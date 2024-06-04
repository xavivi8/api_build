package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);

    User findByUserAndPass(String email, String pass);

    void updateUser(String email, String user_name, String pass, Long id);

    void addUserWithoutImage(String email, String user_name, String pass);
}
