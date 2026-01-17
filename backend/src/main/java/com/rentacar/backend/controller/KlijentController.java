package com.rentacar.backend.controller;

import com.rentacar.backend.dto.CreateKlijentDTO;
import com.rentacar.backend.dto.KlijentDTO;
import com.rentacar.backend.model.Klijent;
import com.rentacar.backend.services.KlijentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klijenti")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class KlijentController {
    private final KlijentService klijentService;


    @GetMapping
    public ResponseEntity<List<KlijentDTO>> getAll(){
        return ResponseEntity.ok(klijentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KlijentDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(klijentService.getById(id));
    }

    @PostMapping("/registracija")
    public ResponseEntity<KlijentDTO> create(@Valid @RequestBody CreateKlijentDTO dto){
        return new ResponseEntity<>(klijentService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KlijentDTO> azuriraj(@PathVariable Integer id, @Valid @RequestBody CreateKlijentDTO dto) {
        return ResponseEntity.ok(klijentService.azurirajKlijenta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Integer id) {
        klijentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
