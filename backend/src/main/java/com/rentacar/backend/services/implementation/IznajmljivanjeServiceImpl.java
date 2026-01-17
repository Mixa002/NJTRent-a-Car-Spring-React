package com.rentacar.backend.services.implementation;

import com.rentacar.backend.dto.CreateIznajmljivanjeDTO;
import com.rentacar.backend.dto.IznajmljivanjeDTO;
import com.rentacar.backend.mapper.IznajmljivanjeMapper;
import com.rentacar.backend.model.Automobil;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.model.Klijent;
import com.rentacar.backend.model.StavkaIznajmljivanja;
import com.rentacar.backend.model.enums.StatusIznajmljivanja;
import com.rentacar.backend.repository.AutomobilRepository;
import com.rentacar.backend.repository.IznajmljivanjeRepository;
import com.rentacar.backend.repository.KlijentRepository;
import com.rentacar.backend.services.IznajmljivanjeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IznajmljivanjeServiceImpl implements IznajmljivanjeService {
    private final IznajmljivanjeRepository iznajmljivanjeRepo;
    private final KlijentRepository klijentRepo;
    private final AutomobilRepository automobilRepo;
    private final IznajmljivanjeMapper iznajmljivanjeMapper;


    @Override
    public List<IznajmljivanjeDTO> getAll() {
        return iznajmljivanjeRepo.findAll().stream()
                .map(iznajmljivanjeMapper::toDTO)
                .collect(Collectors.toList());    }

    @Override
    public IznajmljivanjeDTO getById(Integer id) {
        return iznajmljivanjeRepo.findById(id)
                .map(iznajmljivanjeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Iznajmljivanje nije pronađeno."));    }

    @Override
    @Transactional
    public IznajmljivanjeDTO save(CreateIznajmljivanjeDTO dto) {
        // 1. Pronalaženje klijenta
        Klijent klijent = klijentRepo.findById(dto.klijentId())
                .orElseThrow(() -> new RuntimeException("Klijent nije pronađen."));

        // 2. Inicijalizacija glavnog objekta
        Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
        iznajmljivanje.setKlijent(klijent);
        iznajmljivanje.setOpis(dto.opis()); // <--- DODAJ OVU LINIJU
        iznajmljivanje.setDatumKreiranja(LocalDateTime.now());
        iznajmljivanje.setStatus(StatusIznajmljivanja.CEKA_ODOBRENJE);

        BigDecimal ukupnaSuma = BigDecimal.ZERO;

        // 3. Prolazak kroz stavke iz DTO-a
        for (var stavkaDto : dto.stavke()) {
            Automobil auto = automobilRepo.findById(stavkaDto.automobilId())
                    .orElseThrow(() -> new RuntimeException("Auto nije pronađen."));

            // Matematika: dani * cena
            long dani = ChronoUnit.DAYS.between(stavkaDto.datumOd(), stavkaDto.datumDo());
            if (dani <= 0) dani = 1;

            BigDecimal cenaStavke =
                    auto.getCenaPoDanu().multiply(BigDecimal.valueOf(dani));

            // Kreiranje entiteta stavke (ne treba ti poseban servis za ovo čuvanje)
            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
            stavka.setAutomobil(auto);
            stavka.setDatumOd(stavkaDto.datumOd());
            stavka.setDatumDo(stavkaDto.datumDo());
            stavka.setCenaStavke(cenaStavke);
            stavka.setNapomena(stavkaDto.napomena());

            // POVEZIVANJE: Ključni deo za Hibernate
            stavka.setIznajmljivanje(iznajmljivanje);
            iznajmljivanje.getStavke().add(stavka);

            ukupnaSuma = ukupnaSuma.add(cenaStavke);
        }

        iznajmljivanje.setUkupanIznos(ukupnaSuma);

        // 4. Snimanje - CascadeType.ALL će snimiti i Iznajmljivanje i sve Stavke u bazu
        Iznajmljivanje sacuvano = iznajmljivanjeRepo.save(iznajmljivanje);

        return iznajmljivanjeMapper.toDTO(sacuvano);    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        iznajmljivanjeRepo.deleteById(id);
    }

    @Override
    @Transactional
    public IznajmljivanjeDTO update(Integer id, CreateIznajmljivanjeDTO dto) {
            // 1. Pronađi postojeće iznajmljivanje
            Iznajmljivanje iznajmljivanje = iznajmljivanjeRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Iznajmljivanje nije pronađeno."));

            // 2. Ažuriraj osnovna polja
            iznajmljivanje.setOpis(dto.opis());

            // Ako se klijent promenio:
            Klijent noviKlijent = klijentRepo.findById(dto.klijentId())
                    .orElseThrow(() -> new RuntimeException("Klijent nije pronađen."));
            iznajmljivanje.setKlijent(noviKlijent);

            // 3. Očisti stare stavke (Orphan removal će ih obrisati iz baze)
            iznajmljivanje.getStavke().clear();

            // 4. Ponovi logiku dodavanja stavki kao u save metodi
            BigDecimal ukupnaSuma = BigDecimal.ZERO;

            for (var stavkaDto : dto.stavke()) {
                Automobil auto = automobilRepo.findById(stavkaDto.automobilId())
                        .orElseThrow(() -> new RuntimeException("Auto nije pronađen."));

                long dani = ChronoUnit.DAYS.between(stavkaDto.datumOd(), stavkaDto.datumDo());
                if (dani <= 0) dani = 1;

                BigDecimal cenaStavke = auto.getCenaPoDanu().multiply(BigDecimal.valueOf(dani));

                StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
                stavka.setAutomobil(auto);
                stavka.setDatumOd(stavkaDto.datumOd());
                stavka.setDatumDo(stavkaDto.datumDo());
                stavka.setCenaStavke(cenaStavke);
                stavka.setNapomena(stavkaDto.napomena());
                stavka.setIznajmljivanje(iznajmljivanje);

                iznajmljivanje.getStavke().add(stavka);
                ukupnaSuma = ukupnaSuma.add(cenaStavke);
            }

            iznajmljivanje.setUkupanIznos(ukupnaSuma);

            // 5. Sačuvaj promene
            Iznajmljivanje azurirano = iznajmljivanjeRepo.save(iznajmljivanje);
            return iznajmljivanjeMapper.toDTO(azurirano);
        }
}
