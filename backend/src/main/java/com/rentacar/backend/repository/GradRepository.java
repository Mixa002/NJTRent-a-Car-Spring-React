package com.rentacar.backend.repository;

import com.rentacar.backend.model.Grad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradRepository extends JpaRepository<Grad, Integer> {
}
