package com.rentacar.backend.services.implementation;

import com.rentacar.backend.dto.CreateKlijentDTO;
import com.rentacar.backend.dto.KlijentDTO;
import com.rentacar.backend.mapper.KlijentMapper;
import com.rentacar.backend.model.Klijent;
import com.rentacar.backend.repository.KlijentRepository;
import com.rentacar.backend.services.KlijentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KlijentServiceImpl implements KlijentService {

    private final KlijentRepository klijentRepo;
    private final KlijentMapper klijentMapper;

    @Override
    public List<KlijentDTO> getAll() {
        return klijentRepo.findAll().stream()
                .map(klijentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KlijentDTO getById(Integer id) {
        return klijentRepo.findById(id)
                .map(klijentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Klijent nije pronađen."));
    }

    @Override
    @Transactional
    public KlijentDTO save(CreateKlijentDTO dto) {
        if (klijentRepo.existsByEmail(dto.email())) {
            throw new RuntimeException("Email je već registrovan.");
        }
        if (klijentRepo.existsByKorisnickoIme(dto.korisnickoIme())) {
            throw new RuntimeException("Korisničko ime je zauzeto.");
        }

        Klijent klijent = klijentMapper.toEntity(dto);
        // Ovde bi išao passwordEncoder.encode(dto.lozinka())
        return klijentMapper.toDTO(klijentRepo.save(klijent));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        klijentRepo.deleteById(id);
    }


    @Override
    @Transactional
    public KlijentDTO azurirajKlijenta(Integer id, CreateKlijentDTO dto) {
        Klijent klijent = klijentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Klijent nije pronađen."));

        // Provera emaila ako se menja
        if (!klijent.getEmail().equals(dto.email()) && klijentRepo.existsByEmail(dto.email())) {
            throw new RuntimeException("Novi email je već u upotrebi.");
        }

        klijent.setIme(dto.ime());
        klijent.setPrezime(dto.prezime());
        klijent.setEmail(dto.email());
        klijent.setKorisnickoIme(dto.korisnickoIme());
        klijent.setBrTelefona(dto.brTelefona());

        if (dto.lozinka() != null && !dto.lozinka().isBlank()) {
            klijent.setLozinka(dto.lozinka());
        }

        return klijentMapper.toDTO(klijentRepo.save(klijent));
    }
}
