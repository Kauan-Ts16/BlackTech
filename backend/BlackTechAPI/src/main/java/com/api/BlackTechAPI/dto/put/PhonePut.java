package com.api.BlackTechAPI.dto.put;

import jakarta.validation.constraints.NotBlank;

public record PhonePut(@NotBlank String number, @NotBlank String areaCode, @NotBlank String countryCode) {
}
