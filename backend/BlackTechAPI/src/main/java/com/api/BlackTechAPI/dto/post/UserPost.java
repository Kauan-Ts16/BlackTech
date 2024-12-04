package com.api.BlackTechAPI.dto.post;

import jakarta.validation.constraints.NotBlank;

public record UserPost(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
