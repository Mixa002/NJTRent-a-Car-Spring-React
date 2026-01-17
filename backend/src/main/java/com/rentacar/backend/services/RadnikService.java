package com.rentacar.backend.services;

import com.rentacar.backend.dto.CreateRadnikDTO;
import com.rentacar.backend.dto.RadnikDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.model.Radnik;

import java.util.List;

public interface RadnikService {
    List<RadnikDTO> getAll();//vraca sve radnike
    RadnikDTO getById(Integer id);
    RadnikDTO save(CreateRadnikDTO dto);//kreiraj radnika
    void deleteById(Integer id);
    RadnikDTO azurirajRadnika(Integer id, CreateRadnikDTO dto);
}
