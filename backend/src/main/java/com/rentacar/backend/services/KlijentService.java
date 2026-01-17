package com.rentacar.backend.services;

import com.rentacar.backend.dto.CreateKlijentDTO;
import com.rentacar.backend.dto.KlijentDTO;
import com.rentacar.backend.model.Klijent;

import java.util.List;

public interface KlijentService {
    List<KlijentDTO> getAll();

    KlijentDTO getById(Integer id);

    KlijentDTO save(CreateKlijentDTO klijent);

    void deleteById(Integer id);

    KlijentDTO azurirajKlijenta(Integer id, CreateKlijentDTO dto);
}
