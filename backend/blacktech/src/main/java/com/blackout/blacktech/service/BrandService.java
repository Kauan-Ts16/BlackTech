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
        brandRepository.deleteById(id);
    }

    @Transactional
    public BrandModel update(UUID id, BrandPutDto brandPutDto) {
        brandValidator.validateForUpdate(id, brandPutDto);
        BrandModel brandModel = BrandMapper.toMapper(brandPutDto, findById(id));

        return brandRepository.save(brandModel);
    }

    public BrandModel findById(UUID id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ExceptionGeneric("Brand does not found!", "No brand found with id: "+ id, 404));
    }

    public List<BrandModel> findAll() {
        return brandRepository.findAll();
    }

}
