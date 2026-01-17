package com.rentacar.backend.services.implementation;

import com.rentacar.backend.dto.CreateEkspozituraDTO;
import com.rentacar.backend.dto.EkspozituraDTO;
import com.rentacar.backend.mapper.EkspozituraMapper;
import com.rentacar.backend.model.Ekspozitura;
import com.rentacar.backend.model.Grad;
import com.rentacar.backend.repository.EkspozituraRepository;
import com.rentacar.backend.repository.GradRepository;
import com.rentacar.backend.services.EkspozituraService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EkspozituraServiceImpl implements EkspozituraService {

    private final EkspozituraRepository expoRepo;
    private final GradRepository gradRepo;
    private final EkspozituraMapper expoMapper;


    @Override
    public List<EkspozituraDTO> getAll() {
        return expoRepo.findAll().stream()
                .map(expoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EkspozituraDTO getById(Integer id) {
        return expoRepo.findById(id)
                .map(expoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ekspozitura not found!"));

    }

    @Override
    @Transactional
    public EkspozituraDTO save(CreateEkspozituraDTO dto) {
        Grad grad = gradRepo.findById(dto.gradId())
                .orElseThrow(() -> new RuntimeException("Grad sa ID-jem " + dto.gradId() + " ne postoji"));
        Ekspozitura expo = expoMapper.toEntity(dto);
        expo.setGrad(grad);
        return expoMapper.toDTO(expoRepo.save(expo));
    }

    @Override
    @Transactional
    public EkspozituraDTO azurirajEkspozituru(Integer id, CreateEkspozituraDTO dto) {
        Ekspozitura ekspozitura = expoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ekspozitura nije pronađena."));

        Grad grad = gradRepo.findById(dto.gradId())
                .orElseThrow(() -> new RuntimeException("Grad nije pronađen."));

        ekspozitura.setNaziv(dto.naziv());
        ekspozitura.setAdresa(dto.adresa());
        ekspozitura.setGrad(grad);

        return expoMapper.toDTO(expoRepo.save(ekspozitura));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        expoRepo.deleteById(id);
    }
}
