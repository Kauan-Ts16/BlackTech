package com.api.BlackTechAPI.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressPost(@NotBlank String street, @NotNull int number,
                          String complement, @NotBlank String neighborhood, @NotBlank String city,
                          @NotBlank String state, @NotBlank String postalCode) {
}