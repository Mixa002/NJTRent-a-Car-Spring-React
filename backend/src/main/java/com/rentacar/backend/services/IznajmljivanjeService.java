package com.rentacar.backend.services;

import com.rentacar.backend.dto.CreateIznajmljivanjeDTO;
import com.rentacar.backend.dto.IznajmljivanjeDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import jakarta.validation.Valid;

import java.util.List;

public interface IznajmljivanjeService {
    List<IznajmljivanjeDTO> getAll();

    IznajmljivanjeDTO getById(Integer id);

    IznajmljivanjeDTO save(CreateIznajmljivanjeDTO dto);

    void deleteById(Integer id);

    IznajmljivanjeDTO update(Integer id, @Valid CreateIznajmljivanjeDTO dto);
}
