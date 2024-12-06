package com.api.BlackTechAPI.service;

import com.api.BlackTechAPI.components.validation.UserValidate;
import com.api.BlackTechAPI.dto.post.UserPost;
import com.api.BlackTechAPI.dto.put.UserPut;
import com.api.BlackTechAPI.enums.RoleName;
import com.api.BlackTechAPI.mapper.UserMapper;
import com.api.BlackTechAPI.model.RoleModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.repository.RoleRepository;
import com.api.BlackTechAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidate userValidate;


    @Transactional
    public UserModel save(UserPost userPost) {
        userValidate.isValid(userPost);
        UserModel userModel = UserMapper.toMapper(userPost);
        encodePassword(userModel);
        addRole(userModel);

        return userRepository.save(userModel);
    }

    @Transactional
    public void delete(UUID id) {
        userValidate.validateDelete(id);
        userRepository.deleteById(id);
    }

    public UserModel update(UUID id, UserPut userPut) {
        userValidate.validateUpdate(id, userPut);
        UserModel userModel = UserMapper.toMapper(userPut, findById(id));
        encodePassword(userModel);

        return userRepository.save(userModel);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findById(UUID id) {
        return userValidate.findById(id);
    }

    private void encodePassword(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    }

    private void addRole(UserModel userModel) {
        RoleModel role = roleRepository.findByRoleName(RoleName.ROLE_CLIENT);
        userModel.setRole(role);
    }
}
