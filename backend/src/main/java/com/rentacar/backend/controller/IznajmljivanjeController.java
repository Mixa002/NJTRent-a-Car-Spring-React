package com.rentacar.backend.controller;


import com.rentacar.backend.dto.CreateIznajmljivanjeDTO;
import com.rentacar.backend.dto.IznajmljivanjeDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.services.IznajmljivanjeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iznajmljivanja")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class IznajmljivanjeController {
    private final IznajmljivanjeService iznajmljivanjeService;

    //sva iznajmljvianja
    @GetMapping
    public ResponseEntity<List<IznajmljivanjeDTO>> getAll(){
        return ResponseEntity.ok(iznajmljivanjeService.getAll());
    }

    // Detalji jednog specifičnog iznajmljivanja (ovde će se videti i lista stavki unutar DTO-a)
    @GetMapping("/{id}")
    public ResponseEntity<IznajmljivanjeDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(iznajmljivanjeService.getById(id));
    }

    // Kreiranje iznajmljivanja zajedno sa svim stavkama
    @PostMapping
    public ResponseEntity<IznajmljivanjeDTO> create(@Valid @RequestBody CreateIznajmljivanjeDTO dto){
        return new ResponseEntity<>(iznajmljivanjeService.save(dto), HttpStatus.CREATED);    }

    // Brisanje (otkazivanje) iznajmljivanja i svih njegovih stavki automatski
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        iznajmljivanjeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<IznajmljivanjeDTO> update(@PathVariable Integer id, @Valid @RequestBody CreateIznajmljivanjeDTO dto) {
        return ResponseEntity.ok(iznajmljivanjeService.update(id, dto));
    }
}
