package com.rentacar.backend.services.implementation;


import com.rentacar.backend.dto.AutomobilDTO;
import com.rentacar.backend.dto.CreateAutomobilDTO;
import com.rentacar.backend.mapper.AutomobilMapper;
import com.rentacar.backend.model.Automobil;
import com.rentacar.backend.model.Ekspozitura;
import com.rentacar.backend.repository.AutomobilRepository;
import com.rentacar.backend.repository.EkspozituraRepository;
import com.rentacar.backend.services.AutomobilService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutomobilServiceImpl implements AutomobilService {

    private final AutomobilRepository automobilRepo;
    private final EkspozituraRepository ekspozituraRepo; // Treba nam zbog povezivanja
    private final AutomobilMapper automobilMapper;


    @Override
    public List<AutomobilDTO> getAll() {
        return automobilRepo.findAll()
                .stream()
                .map(automobilMapper::toDTO)//Svaki entitet u DTO
                .collect(Collectors.toList());
    }

    @Override
    public AutomobilDTO getAutomobilByID(Integer id) {
        Automobil auto = automobilRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Automobil not found!"));
        return automobilMapper.toDTO(auto);
    }

    @Override
    @Transactional // Osigurava da se sve izvrši kako treba ili ništa
    public AutomobilDTO save(CreateAutomobilDTO dto) {
        // 1. DTO -> Entitet
        Automobil automobil = automobilMapper.toEntity(dto);

        // 2. Pronalaženje ekspoziture po ID-ju iz DTO-a
        Ekspozitura ekspo = ekspozituraRepo.findById(dto.ekspozituraId())
                .orElseThrow(() -> new RuntimeException("Ekspozitura not found!"));

        // 3. Povezivanje
        automobil.setTrenutnaEkspozitura(ekspo);

        // 4. Snimanje i povratak DTO-a
        Automobil sacuvan = automobilRepo.save(automobil);
        return automobilMapper.toDTO(sacuvan);
    }

    @Override
    public void deleteByID(Integer id) {
        if (!automobilRepo.existsById(id)) {
            throw new RuntimeException("Cannot delete. Automobil not found!");
        }
        automobilRepo.deleteById(id);
    }

    @Override
    public AutomobilDTO update(Integer id, CreateAutomobilDTO dto) {
        Automobil postojeci = automobilRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Automobil nije pronađen"));

        // Ovde prepisuješ podatke iz DTO-a u postojeći entitet
        postojeci.setMarka(dto.marka());
        postojeci.setModel(dto.model());
        postojeci.setCenaPoDanu(dto.cenaPoDanu());
        postojeci.setStatus(dto.status());
        postojeci.setRegBroj(dto.regBroj());
        postojeci.setMenjac(dto.menjac());
        postojeci.setGorivo(dto.gorivo());
        postojeci.setGodiste(dto.godiste());
        postojeci.setSlikaURL(dto.slikaURL());
        Ekspozitura novaEkspo = ekspozituraRepo.findById(dto.ekspozituraId())
                .orElseThrow(() -> new EntityNotFoundException("Ekspozitura nije pronađena"));
        postojeci.setTrenutnaEkspozitura(novaEkspo);

        Automobil sacuvan = automobilRepo.save(postojeci);
        return automobilMapper.toDTO(sacuvan); // Mapper koji već koristiš
    }

}
