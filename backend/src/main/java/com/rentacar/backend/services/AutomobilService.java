package com.rentacar.backend.services;

import com.rentacar.backend.dto.AutomobilDTO;
import com.rentacar.backend.dto.CreateAutomobilDTO;
import com.rentacar.backend.model.Automobil;

import java.util.List;

public interface AutomobilService {
    List<AutomobilDTO> getAll();

    AutomobilDTO getAutomobilByID(Integer id);

    AutomobilDTO save(CreateAutomobilDTO automobil);

    void deleteByID(Integer id);

    AutomobilDTO update(Integer id, CreateAutomobilDTO dto);
}
