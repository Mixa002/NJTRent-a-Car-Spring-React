package com.rentacar.backend.repository;

import com.rentacar.backend.model.Automobil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobilRepository extends JpaRepository<Automobil, Integer> {
}
