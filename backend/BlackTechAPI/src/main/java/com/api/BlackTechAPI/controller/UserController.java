package com.api.BlackTechAPI.controller;

import com.api.BlackTechAPI.dto.post.UserPost;
import com.api.BlackTechAPI.dto.put.UserPut;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/black-tech-api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody @Valid UserPost userPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id")UUID id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(@PathVariable(name = "id")UUID id, @RequestBody @Valid UserPut userPut) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userPut));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getOne(@PathVariable(name = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
}
