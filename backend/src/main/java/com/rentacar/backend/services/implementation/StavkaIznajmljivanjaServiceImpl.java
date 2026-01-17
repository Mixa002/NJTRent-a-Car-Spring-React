package com.rentacar.backend.services.implementation;

import com.rentacar.backend.dto.StavkaIznajmljivanjaDTO;
import com.rentacar.backend.mapper.StavkaIznajmljivanjaMapper;
import com.rentacar.backend.model.StavkaIznajmljivanja;
import com.rentacar.backend.repository.RadnikEkspoRepository;
import com.rentacar.backend.repository.StavkaIznajmljivanjaRepository;
import com.rentacar.backend.services.StavkaIznajmljivanjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StavkaIznajmljivanjaServiceImpl implements StavkaIznajmljivanjaService {
    private final StavkaIznajmljivanjaRepository stavkaIznajmljivanjaRepo;
    private final StavkaIznajmljivanjaMapper stavkaMapper;


    @Override
    public List<StavkaIznajmljivanjaDTO> getAll() {
        return stavkaIznajmljivanjaRepo.findAll().stream()
                .map(stavkaMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public StavkaIznajmljivanja getById(Integer id) {
//        return stavkaIznajmljivanjaRepo.findById(id).orElseThrow(() -> new RuntimeException("StavkaIznajmljvanja not found!"));
//    }
//
//    @Override
//    public StavkaIznajmljivanja save(StavkaIznajmljivanja si) {
//        return stavkaIznajmljivanjaRepo.save(si);
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        stavkaIznajmljivanjaRepo.deleteById(id);
//    }
}
