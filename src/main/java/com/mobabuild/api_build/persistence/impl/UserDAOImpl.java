package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.persistence.IUserDAO;
import com.mobabuild.api_build.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements IUserDAO {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserAndPass(String email, String pass) {
        return userRepository.findByUserAndPass(email, pass);
    }

    @Override
    public void updateUser(String email, String user_name, String pass, Long id) {
        userRepository.updateUser(email, user_name, pass, id);
    }

    @Override
    public void addUserWithoutImage(String email, String user_name, String pass) {
        userRepository.addUserWithoutImage(email, user_name, pass);
    }
}
