package com.api.BlackTechAPI.controller;

import com.api.BlackTechAPI.dto.post.PhonePost;
import com.api.BlackTechAPI.dto.put.PhonePut;
import com.api.BlackTechAPI.model.PhoneModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/black-tech-api/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    @PostMapping
    public ResponseEntity<PhoneModel> save(@RequestBody @Valid PhonePost phonePost, @AuthenticationPrincipal UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.save(phonePost, userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id")Integer id, @AuthenticationPrincipal UserModel userModel) {
        phoneService.delete(id, userModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneModel> update(@PathVariable(name = "id")Integer id, @RequestBody @Valid PhonePut phonePut) {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.update(id, phonePut));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneModel> findById(@PathVariable(name = "id")Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.findById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<PhoneModel>> findAllByUserModel(@AuthenticationPrincipal UserModel userModel) {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.findAllByUserModel(userModel));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhoneModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.findAll());
    }
}
