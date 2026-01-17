package com.rentacar.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateGradDTO(
        @NotBlank(message = "Naziv grada je obavezan")
        String naziv,

        @NotBlank(message = "Poštanski broj je obavezan")
        @Pattern(regexp = "^[0-9]{5}$", message = "Poštanski broj mora imati tačno 5 cifara")
        String postanskiBroj
) {
}
