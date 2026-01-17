package com.rentacar.backend.services.implementation;

import com.rentacar.backend.dto.CreateRadnikDTO;
import com.rentacar.backend.dto.RadnikDTO;
import com.rentacar.backend.mapper.RadnikMapper;
import com.rentacar.backend.model.Radnik;
import com.rentacar.backend.repository.RadnikEkspoRepository;
import com.rentacar.backend.repository.RadnikRepository;
import com.rentacar.backend.services.RadnikService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RadnikServiceImpl implements RadnikService {
    private final RadnikRepository radnikRepo;
    private final RadnikMapper radnikMapper;

    @Override
    public List<RadnikDTO> getAll() {
        return radnikRepo.findAll().stream()
                .map(radnikMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RadnikDTO getById(Integer id) {
        return radnikRepo.findById(id)
                .map(radnikMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Radnik nije pronađen."));    }

    @Override
    @Transactional
    public RadnikDTO save(CreateRadnikDTO dto) {
        if (radnikRepo.existsByEmail(dto.email())) {
            throw new RuntimeException("Email je već u upotrebi.");
        }
        Radnik radnik = radnikMapper.toEntity(dto);
        // Ovde bi išao passwordEncoder.encode(dto.lozinka())
        return radnikMapper.toDTO(radnikRepo.save(radnik));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        radnikRepo.deleteById(id);
    }

    @Override
    @Transactional
    public RadnikDTO azurirajRadnika(Integer id, CreateRadnikDTO dto) {
        // 1. Pronađi postojećeg radnika ili baci grešku
        Radnik radnik = radnikRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Radnik sa ID-em " + id + " nije pronađen."));

        // 2. Provera jedinstvenosti emaila (ako se email menja)
        if (!radnik.getEmail().equals(dto.email()) && radnikRepo.existsByEmail(dto.email())) {
            throw new RuntimeException("Nije moguće ažurirati: Email je već u upotrebi.");
        }

        // 3. Provera korisničkog imena
        if (!radnik.getKorisnickoIme().equals(dto.korisnickoIme()) && radnikRepo.existsByKorisnickoIme(dto.korisnickoIme())) {
            throw new RuntimeException("Nije moguće ažurirati: Korisničko ime je zauzeto.");
        }

        // 4. Setovanje novih vrednosti
        radnik.setIme(dto.ime());
        radnik.setPrezime(dto.prezime());
        radnik.setEmail(dto.email());
        radnik.setKorisnickoIme(dto.korisnickoIme());

        // Opciono: ažuriranje lozinke (samo ako je poslata nova vrednost)
        if (dto.lozinka() != null && !dto.lozinka().isBlank()) {
            radnik.setLozinka(dto.lozinka()); // Ovde bi išao passwordEncoder
        }

        // 5. Čuvanje i vraćanje nazad u DTO formatu
        Radnik izmenjeniRadnik = radnikRepo.save(radnik);
        return radnikMapper.toDTO(izmenjeniRadnik);
    }
}
