package com.rentacar.backend.controller;

import com.rentacar.backend.dto.CreateEkspozituraDTO;
import com.rentacar.backend.dto.EkspozituraDTO;
import com.rentacar.backend.model.Ekspozitura;
import com.rentacar.backend.model.Grad;
import com.rentacar.backend.services.EkspozituraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ekspoziture")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class EkspozituraController  {
    private final EkspozituraService expozituraService;



    @GetMapping
    public ResponseEntity<List<EkspozituraDTO>> getAll() {
        return ResponseEntity.ok(expozituraService.getAll());    }

    @GetMapping("/{id}")
    public ResponseEntity<EkspozituraDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(expozituraService.getById(id));    }

    @PutMapping("/{id}")
    public ResponseEntity<EkspozituraDTO> azuriraj(@PathVariable Integer id, @Valid @RequestBody CreateEkspozituraDTO dto) {
        return ResponseEntity.ok(expozituraService.azurirajEkspozituru(id, dto));
    }

    @PostMapping
    public ResponseEntity<EkspozituraDTO> create(@Valid @RequestBody CreateEkspozituraDTO dto) {
        return new ResponseEntity<>(expozituraService.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        expozituraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
