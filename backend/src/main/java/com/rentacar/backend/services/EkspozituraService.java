package com.rentacar.backend.services;

import com.rentacar.backend.dto.CreateEkspozituraDTO;
import com.rentacar.backend.dto.EkspozituraDTO;
import com.rentacar.backend.model.Ekspozitura;


import java.util.List;

public interface EkspozituraService {
    List<EkspozituraDTO> getAll();

    EkspozituraDTO getById(Integer id);

    EkspozituraDTO save(CreateEkspozituraDTO grad);

    void deleteById(Integer id);

    EkspozituraDTO azurirajEkspozituru(Integer id, CreateEkspozituraDTO dto);
}
