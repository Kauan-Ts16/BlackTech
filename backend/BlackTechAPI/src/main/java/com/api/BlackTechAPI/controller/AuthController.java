package com.api.BlackTechAPI.controller;

import com.api.BlackTechAPI.dto.ResponseDto;
import com.api.BlackTechAPI.dto.post.AuthPost;
import com.api.BlackTechAPI.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/black-tech-api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid AuthPost authPost) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(authPost));
    }

    @GetMapping("/verifyToken")
    public String verifyToken() {return "Success";}

}
