package com.rentacar.backend.services;

import com.rentacar.backend.dto.CreateRadnikEkspoDTO;
import com.rentacar.backend.dto.RadnikEkspoDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.model.RadnikEkspo;
import com.rentacar.backend.model.RadnikEkspoKey;
import jakarta.validation.Valid;

import java.util.List;

public interface RadnikEkspoService {
    RadnikEkspoDTO save(CreateRadnikEkspoDTO dto);

    List<RadnikEkspoDTO> getAll();

    List<RadnikEkspoDTO> getByRadnikId(Integer radnikId);


    void delete(RadnikEkspoKey id);

    RadnikEkspoDTO update(RadnikEkspoKey id, @Valid CreateRadnikEkspoDTO dto);
}
