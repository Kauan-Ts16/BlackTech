package com.api.BlackTechAPI.controller;

import com.api.BlackTechAPI.dto.post.AddressPost;
import com.api.BlackTechAPI.dto.put.AddressPut;
import com.api.BlackTechAPI.model.AddressModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/black-tech-api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping
    public ResponseEntity<AddressModel> save(@RequestBody @Valid AddressPost addressPost, @AuthenticationPrincipal UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(addressPost, userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id")Integer id, @AuthenticationPrincipal UserModel userModel) {
        addressService.delete(id, userModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> update(@PathVariable(name = "id")Integer id, @RequestBody @Valid AddressPut addressPut) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(id, addressPut));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> findById(@PathVariable(name = "id")Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<AddressModel>> findAllByUserModel(@AuthenticationPrincipal UserModel userModel) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAllByUserModel(userModel));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
    }

}
