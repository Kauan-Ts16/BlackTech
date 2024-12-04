package com.api.BlackTechAPI.dto.put;

import jakarta.validation.constraints.NotBlank;

public record UserPut(@NotBlank String password) {
}
