package com.rentacar.backend.repository;

import com.rentacar.backend.model.Iznajmljivanje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IznajmljivanjeRepository extends JpaRepository<Iznajmljivanje, Integer> {
}
