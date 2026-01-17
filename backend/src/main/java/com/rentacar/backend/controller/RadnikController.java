package com.rentacar.backend.controller;


import com.rentacar.backend.dto.CreateRadnikDTO;
import com.rentacar.backend.dto.RadnikDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.model.Radnik;
import com.rentacar.backend.services.IznajmljivanjeService;
import com.rentacar.backend.services.RadnikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/radnici")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RadnikController {
    private final RadnikService radnikService;



    @GetMapping
    public ResponseEntity<List<RadnikDTO>> getAll(){
        return ResponseEntity.ok(radnikService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RadnikDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(radnikService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RadnikDTO> create(@Valid @RequestBody CreateRadnikDTO dto){
        return new ResponseEntity<>(radnikService.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        radnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RadnikDTO> azuriraj(@PathVariable Integer id, @Valid @RequestBody CreateRadnikDTO dto) {
        RadnikDTO azuriran = radnikService.azurirajRadnika(id, dto);
        return ResponseEntity.ok(azuriran);
    }
}
