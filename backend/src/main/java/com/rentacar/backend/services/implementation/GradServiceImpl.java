package com.rentacar.backend.services.implementation;


import com.rentacar.backend.dto.CreateGradDTO;
import com.rentacar.backend.dto.GradDTO;
import com.rentacar.backend.mapper.GradMapper;
import com.rentacar.backend.model.Grad;
import com.rentacar.backend.repository.GradRepository;
import com.rentacar.backend.services.GradService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradServiceImpl implements GradService {
    private final GradRepository gradRepo;
    private final GradMapper gradMapper;

    @Override
    public List<GradDTO> getAll() {
        return gradRepo.findAll().stream()
                .map(gradMapper::toDTO)
                .toList();    }

    @Override
    public GradDTO getById(Integer id) {
        return gradRepo.findById(id)
                .map(gradMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Grad nije pronađen!"));    }

    @Override
    public GradDTO save(CreateGradDTO dto) {
        Grad grad = gradMapper.toEntity(dto);
        return gradMapper.toDTO(gradRepo.save(grad));    }

    @Override
    public void deleteById(Integer id) {
        gradRepo.deleteById(id);
    }
}
