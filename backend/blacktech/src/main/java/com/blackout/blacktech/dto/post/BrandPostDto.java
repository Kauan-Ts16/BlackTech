package com.blackout.blacktech.dto.post;

import jakarta.validation.constraints.NotBlank;

public record BrandPostDto(@NotBlank String name) {
}
