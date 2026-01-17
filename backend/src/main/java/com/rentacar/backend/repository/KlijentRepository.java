package com.rentacar.backend.repository;

import com.rentacar.backend.model.Klijent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlijentRepository extends JpaRepository<Klijent, Integer> {
    Optional<Klijent> findByJmbg(String jmbg);

    boolean existsByEmail(String email);

    boolean existsByKorisnickoIme(String s);
}
