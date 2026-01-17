package com.rentacar.backend.services;

import com.rentacar.backend.dto.CreateGradDTO;
import com.rentacar.backend.dto.GradDTO;
import com.rentacar.backend.model.Grad;

import java.util.List;

public interface GradService {
    List<GradDTO> getAll();

    GradDTO getById(Integer id);

    GradDTO save(CreateGradDTO grad);

    void deleteById(Integer id);
}
