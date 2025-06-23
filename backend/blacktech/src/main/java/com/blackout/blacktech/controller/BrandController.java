package com.blackout.blacktech.controller;

import com.blackout.blacktech.dto.post.BrandPostDto;
import com.blackout.blacktech.dto.put.BrandPutDto;
import com.blackout.blacktech.model.BrandModel;
import com.blackout.blacktech.service.BrandService;
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


    // === CLIENT ACCESS ===

    @GetMapping("/{id}")
    public ResponseEntity<BrandModel> findByIdAndIsActiveTrue(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findByIdAndIsActiveTrue(id));
    }

    @GetMapping
    public ResponseEntity<List<BrandModel>> findAllByIsActiveTrue() {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAllByIsActiveTrue());
    }


    // === ADMIN ACCESS ===

    @PostMapping
    public ResponseEntity<BrandModel> save(@RequestBody BrandPostDto brandPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.save(brandPostDto));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id")UUID id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<BrandModel> update(@PathVariable(name = "id") UUID id, @RequestBody BrandPutDto brandPutDto) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(id, brandPutDto));
    }

    @PatchMapping("/admin/{id}/reactive")
    public ResponseEntity<BrandModel> reactive(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.reactive(id));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<BrandModel> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findById(id));
    }

    @GetMapping("/admin/inactive/{id}")
    public ResponseEntity<BrandModel> findByIdAndIsActiveFalse(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findByIdAndIsActiveFalse(id));
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<BrandModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll());
    }

    @GetMapping("/admin/inactive")
    public ResponseEntity<List<BrandModel>> findAllByIsActiveFalse() {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAllByIsActiveFalse());
    }

}
