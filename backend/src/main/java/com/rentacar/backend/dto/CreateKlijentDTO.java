package com.rentacar.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateKlijentDTO(
        @NotBlank(message = "Ime je obavezno")
        String ime,

        @NotBlank(message = "Prezime je obavezno")
        String prezime,

        @Email(message = "Email mora biti validan")
        @NotBlank(message = "Email je obavezan")
        String email,

        @NotBlank(message = "Korisničko ime je obavezno")
        String korisnickoIme,

        @NotBlank(message = "Lozinka je obavezna")
        @Size(min = 6, message = "Lozinka mora imati najmanje 6 karaktera")
        String lozinka,

        @NotBlank(message = "Broj telefona je obavezan")
        String brTelefona,

        @NotBlank(message = "JMBG je obavezan")
        @Size(min = 13, max = 13, message = "JMBG mora imati tačno 13 karaktera")
        @Pattern(regexp = "^[0-9]*$", message = "JMBG mora sadržati samo cifre")
                String jmbg
) { }
