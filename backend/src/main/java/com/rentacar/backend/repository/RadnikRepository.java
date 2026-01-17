package com.rentacar.backend.repository;

import com.rentacar.backend.model.Radnik;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RadnikRepository extends JpaRepository<Radnik, Integer> {
    boolean existsByEmail(String email);

    boolean existsByKorisnickoIme(String s);

    Optional<Radnik> findByKorisnickoIme(String korisnickoIme);
}
