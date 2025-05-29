package com.blackout.blacktech.dto.put;

import jakarta.validation.constraints.NotBlank;

public record BrandPutDto(@NotBlank String name) {
}
