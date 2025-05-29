package com.blackout.blacktech.validation;

import com.blackout.blacktech.dto.post.BrandPostDto;
import com.blackout.blacktech.dto.put.BrandPutDto;
import com.blackout.blacktech.exception.ExceptionGeneric;
import com.blackout.blacktech.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BrandValidator {

    @Autowired
    private BrandRepository brandRepository;


    public void validateForCreate(BrandPostDto brandPostDto) {
        validateNameForCreate(brandPostDto.name());
    }

    public void validateForUpdate(UUID id, BrandPutDto brandPutDto) {
        validateBrandExists(id);
        validateNameForUpdate(brandPutDto.name(), id);
    }

    public void validateForDelete(UUID id) {
        validateBrandExists(id);
    }

    private void validateNameForCreate(String name) {
        validateNameFormat(name);

        if (brandRepository.existsByName(name)) {
            throw new ExceptionGeneric("Invalid brand name!", "A brand with this name already exists.", 400);
        }
    }

    private void validateNameForUpdate(String name, UUID id) {
        validateNameFormat(name);

        if (brandRepository.existsByNameAndIdNot(name, id)) {
            throw new ExceptionGeneric("Invalid brand name!", "A brand with this name already exists.", 400);
        }
    }

    private void validateNameFormat(String name) {
        if (name == null || name.isBlank()) {
            throw new ExceptionGeneric("Invalid brand name!", "The brand name is required.", 400);
        }

        if (name.length() < 2 || name.length() > 50) {
            throw new ExceptionGeneric("Invalid brand name!", "The brand name must be between 2 and 50 characters.", 400);
        }
    }

    private void validateBrandExists(UUID id) {
        if (!brandRepository.existsById(id)) {
            throw new ExceptionGeneric("Brand does not exist!", "No brand found with id: "+ id , 404);
        }
    }
}
