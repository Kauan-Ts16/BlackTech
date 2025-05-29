package com.blackout.blacktech.controller;

import com.blackout.blacktech.dto.post.BrandPostDto;
import com.blackout.blacktech.dto.put.BrandPutDto;
import com.blackout.blacktech.model.BrandModel;
import com.blackout.blacktech.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/black-tech/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandModel> save(@RequestBody @Valid BrandPostDto brandPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.save(brandPostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id")UUID id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandModel> update(@PathVariable(name = "id") UUID id, @RequestBody @Valid BrandPutDto brandPutDto) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(id, brandPutDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandModel> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BrandModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll());
    }

}
