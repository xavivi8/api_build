package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.UserComand;
import com.mobabuild.api_build.controller.dto.AuthorityDTO;
import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.persistence.IUserDAO;
import com.mobabuild.api_build.service.IUserService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User findByUserAndPass(String email, String pass) {
        return userDAO.findByUserAndPass(email, pass);
    }

    @Override
    public UserDTO updateUser(UserComand userComand) {
        Optional<User> existingUserOptional = userDAO.findById(userComand.getId());
        List<Authority> authorities = new ArrayList<>();
        if (existingUserOptional.isPresent()) {
            User userOptionalExist = existingUserOptional.get();
            userOptionalExist.setEmail(userComand.getEmail());
            userOptionalExist.setUser_name(userComand.getUser_name());
            userOptionalExist.setPass(userComand.getPass());
            userOptionalExist.setImage(BlobUtils.bytesToBlob(userComand.getImage()));
            userOptionalExist.setAuthorities(userComand.getAuthorities());

            List<AuthorityDTO> authorityDTOs = userOptionalExist.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            UserDTO userDTO = UserDTO.builder()
                    .id(userOptionalExist.getId())
                    .email(userOptionalExist.getEmail())
                    .user_name(userOptionalExist.getUser_name())
                    .pass(userOptionalExist.getPass())
                    .image(BlobUtils.blobToBytes(userOptionalExist.getImage()))
                    .authorities(authorityDTOs)
                    .build();

            userDAO.save(userOptionalExist);
            return userDTO;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User addUserWithoutImage(String email, String user_name, String pass) {
        User newUser = User.builder()
                .email(email)
                .user_name(user_name)
                .pass(pass)
                .build();
        return userDAO.save(newUser);
    }
}
