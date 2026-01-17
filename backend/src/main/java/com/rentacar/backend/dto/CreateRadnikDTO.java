package com.rentacar.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRadnikDTO(
        @NotBlank(message = "Ime je obavezno")
        String ime,

        @NotBlank(message = "Prezime je obavezno")
        String prezime,

        @NotBlank(message = "Korisničko ime je obavezno")
        String korisnickoIme,

        @Email(message = "Email mora biti u ispravnom formatu")
        @NotBlank(message = "Email je obavezan")
        String email,

        @NotBlank(message = "Lozinka je obavezna")
        @Size(min = 8, message = "Lozinka mora imati najmanje 8 karaktera")
        String lozinka
) {
}
