package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.pass = ?2")
    User findByUserAndPass(String email, String pass);

    Optional<User> findByEmail(String email);

    // Método para actualizar un usuario existente
    @Query("UPDATE User u SET u.email = ?1, u.user_name = ?2, u.pass = ?3 WHERE u.id = ?4")
    void updateUser(String email, String user_name, String pass, Long id);

    // Método para añadir un usuario sin imagen
    @Query("INSERT INTO User (email, user_name, pass) VALUES (?1, ?2, ?3)")
    void addUserWithoutImage(String email, String user_name, String pass);
}
