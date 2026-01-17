package com.rentacar.backend.services.implementation;

import com.rentacar.backend.dto.CreateRadnikEkspoDTO;
import com.rentacar.backend.dto.RadnikEkspoDTO;
import com.rentacar.backend.mapper.RadnikEkspoMapper;
import com.rentacar.backend.mapper.RadnikMapper;
import com.rentacar.backend.model.*;
import com.rentacar.backend.repository.EkspozituraRepository;
import com.rentacar.backend.repository.IznajmljivanjeRepository;
import com.rentacar.backend.repository.RadnikEkspoRepository;
import com.rentacar.backend.repository.RadnikRepository;
import com.rentacar.backend.services.RadnikEkspoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RadnikEkspoServiceImpl implements RadnikEkspoService {
    private final RadnikEkspoRepository radnikEkspoRepo;
    private final RadnikRepository radnikRepo;
    private final EkspozituraRepository expoRepo;
    private final RadnikEkspoMapper radnikEkspoMapper;
    private final RadnikMapper radnikMapper;


    @Override
    public List<RadnikEkspoDTO> getAll() {
        return radnikEkspoRepo.findAll().stream()
                .map(radnikEkspoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RadnikEkspoDTO> getByRadnikId(Integer id) {
        // Pretpostavka da u repozitorijumu imaš metodu findById_RadnikID
        // Korištenje nove metode iz repozitorijuma
        return radnikEkspoRepo.findById_RadnikID(id).stream()
                .map(radnikEkspoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RadnikEkspoDTO save(CreateRadnikEkspoDTO dto) {
        // 1. Proveri da li radnik postoji
        Radnik radnik = radnikRepo.findById(dto.radnikId())
                .orElseThrow(() -> new RuntimeException("Radnik nije pronađen."));

        // 2. Proveri da li ekspozitura postoji
        Ekspozitura ekspo = expoRepo.findById(dto.ekspozituraId())
                .orElseThrow(() -> new RuntimeException("Ekspozitura nije pronađena."));

        // 3. Mapiraj DTO u entitet (Mapper će popuniti id.radnikID i id.ekspozituraID)
        RadnikEkspo radnikEkspo = radnikEkspoMapper.toEntity(dto);

        // 4. Postavi objekte za Hibernate @MapsId mehanizam
        radnikEkspo.setRadnik(radnik);
        radnikEkspo.setEkspozitura(ekspo);

        return radnikEkspoMapper.toDTO(radnikEkspoRepo.save(radnikEkspo));
    }
    @Override
    @Transactional
    public RadnikEkspoDTO update(RadnikEkspoKey id, CreateRadnikEkspoDTO dto) {
        RadnikEkspo postojeci = radnikEkspoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Zapis nije pronađen za date ID-jeve"));

        // Ažuriramo samo satnicu (ID-jevi se obično ne menjaju kod kompozitnih ključeva)
        postojeci.setSatnica(dto.satnica());

        RadnikEkspo azuriran = radnikEkspoRepo.save(postojeci);
        return radnikEkspoMapper.toDTO(azuriran);
    }

    @Override
    @Transactional
    public void delete(RadnikEkspoKey id) {
        if (!radnikEkspoRepo.existsById(id)) {
            throw new RuntimeException("Zapis ne postoji.");
        }
        radnikEkspoRepo.deleteById(id);
    }
}
