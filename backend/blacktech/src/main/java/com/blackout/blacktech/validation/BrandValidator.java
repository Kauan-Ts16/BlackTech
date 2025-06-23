package com.blackout.blacktech.validation;

import com.blackout.blacktech.dto.post.BrandPostDto;
import com.blackout.blacktech.dto.put.BrandPutDto;
import com.blackout.blacktech.exception.ExceptionGeneric;
import com.blackout.blacktech.model.BrandModel;
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

    public void validateForDelete(UUID id) {
        validateActiveBrandExists(id);
    }

    public void validateForUpdate(UUID id, BrandPutDto brandPutDto) {
        validateActiveBrandExists(id);
        validateNameForUpdate(brandPutDto.name(), id);
    }

    public void validateForReactivation(UUID id) {
        if (!brandRepository.existsByIdAndIsActiveFalse(id)) {
            throw new ExceptionGeneric("Brand not found!", "No inactive brand found with id: "+ id, 404);
        }
    }

    private void validateNameForCreate(String name) {
        validateNameFormat(name);

        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new ExceptionGeneric("Invalid brand name!", "A brand with this name already exists.", 400);
        }
    }

    private void validateNameForUpdate(String name, UUID id) {
        validateNameFormat(name);

        BrandModel brand = brandRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ExceptionGeneric("Brand not found!", "No brand found with id: "+ id, 404));

        if (brand.getName().trim().equalsIgnoreCase(name.trim())) {
            throw new ExceptionGeneric("Invalid brand name!", "The provided brand name is the same as the current one.", 400);
        }

        if (brandRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
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

    private void validateActiveBrandExists(UUID id) {
        if (!brandRepository.existsByIdAndIsActiveTrue(id)) {
            throw new ExceptionGeneric("Brand not exist!", "No brand found with id: "+ id , 404);
        }
    }

}
