package com.api.BlackTechAPI.service;

import com.api.BlackTechAPI.components.security.TokenService;
import com.api.BlackTechAPI.components.validation.UserValidate;
import com.api.BlackTechAPI.dto.ResponseDto;
import com.api.BlackTechAPI.dto.post.AuthPost;
import com.api.BlackTechAPI.exception.ExceptionGeneric;
import com.api.BlackTechAPI.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    public ResponseDto login(AuthPost authPost) {

        UserModel user = userValidate.findByEmail(authPost.email());
        UUID id = user.getUserId();

        if (passwordEncoder.matches(authPost.password(), user.getPassword())) {

            String token = tokenService.generateToken(user);

            return new ResponseDto(token, id);
        }

        throw new ExceptionGeneric("Login error!", "Invalid credentials. Check your password", 401);
    }

}
