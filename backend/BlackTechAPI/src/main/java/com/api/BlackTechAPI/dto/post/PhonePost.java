package com.api.BlackTechAPI.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PhonePost(@NotBlank String number, @NotBlank String areaCode, @NotBlank String countryCode) {
}
