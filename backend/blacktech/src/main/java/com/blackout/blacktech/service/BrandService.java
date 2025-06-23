package com.blackout.blacktech.service;

import com.blackout.blacktech.dto.post.BrandPostDto;
import com.blackout.blacktech.dto.put.BrandPutDto;
import com.blackout.blacktech.exception.ExceptionGeneric;
import com.blackout.blacktech.mapper.BrandMapper;
import com.blackout.blacktech.model.BrandModel;
import com.blackout.blacktech.repository.BrandRepository;
import com.blackout.blacktech.validation.BrandValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandValidator brandValidator;


    @Transactional
    public BrandModel save(BrandPostDto brandPostDto) {
        brandValidator.validateForCreate(brandPostDto);
        BrandModel brandModel = BrandMapper.toMapper(brandPostDto);

        return brandRepository.save(brandModel);
    }

    @Transactional
    public void delete(UUID id) {
        brandValidator.validateForDelete(id);
        BrandModel brandModel = findById(id);
        brandModel.setActive(false);
        brandModel.setUpdateDate(LocalDateTime.now());

        brandRepository.save(brandModel);
    }

    @Transactional
    public BrandModel update(UUID id, BrandPutDto brandPutDto) {
        brandValidator.validateForUpdate(id, brandPutDto);
        BrandModel brandModel = BrandMapper.toMapper(brandPutDto, findById(id));

        return brandRepository.save(brandModel);
    }

    @Transactional
    public BrandModel reactive(UUID id) {
        brandValidator.validateForReactivation(id);
        BrandModel brandModel = findById(id);
        brandModel.setActive(true);
        brandModel.setUpdateDate(LocalDateTime.now());

        return brandRepository.save(brandModel);
    }

    public BrandModel findById(UUID id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ExceptionGeneric("Brand not found!", "No brand found with id: "+ id, 404));
    }

    public BrandModel findByIdAndIsActiveTrue(UUID id) {
        return brandRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ExceptionGeneric("Brand not found!", "No brand found with id: "+ id, 404));
    }

    public BrandModel findByIdAndIsActiveFalse(UUID id) {
        return brandRepository.findByIdAndIsActiveFalse(id)
                .orElseThrow(() -> new ExceptionGeneric("Brand not found!", "No inactive brand found with id: "+ id, 404));
    }

    public List<BrandModel> findAll() {
        return brandRepository.findAll();
    }

    public List<BrandModel> findAllByIsActiveTrue() {
        return brandRepository.findAllByIsActiveTrue();
    }

    public List<BrandModel> findAllByIsActiveFalse() {
        return brandRepository.findAllByIsActiveFalse();
    }

}
