package com.rentacar.backend.services;

import com.rentacar.backend.dto.StavkaIznajmljivanjaDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.model.StavkaIznajmljivanja;

import java.util.List;

public interface StavkaIznajmljivanjaService {
    List<StavkaIznajmljivanjaDTO> getAll();

//    StavkaIznajmljivanja getById(Integer id);
//
//    StavkaIznajmljivanja save(StavkaIznajmljivanja iz);
//
//    void deleteById(Integer id);
}
