package com.rentacar.backend.controller;

import com.rentacar.backend.dto.CreateRadnikEkspoDTO;
import com.rentacar.backend.dto.RadnikEkspoDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import com.rentacar.backend.model.RadnikEkspo;
import com.rentacar.backend.model.RadnikEkspoKey;
import com.rentacar.backend.services.IznajmljivanjeService;
import com.rentacar.backend.services.RadnikEkspoService;
import com.rentacar.backend.services.RadnikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/radnik-ekspo")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RadnikEkspoController {
    private final RadnikEkspoService radnikEkspoService;

    @GetMapping
    public ResponseEntity<List<RadnikEkspoDTO>> getAll(){
        return ResponseEntity.ok(radnikEkspoService.getAll());
    }

    @GetMapping("/radnik/{id}")
    public ResponseEntity<List<RadnikEkspoDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(radnikEkspoService.getByRadnikId(id));    }

    @PostMapping
    public ResponseEntity<RadnikEkspoDTO> create(@Valid @RequestBody CreateRadnikEkspoDTO dto){
        RadnikEkspoDTO noviZapis = radnikEkspoService.save(dto);
        return new ResponseEntity<>(noviZapis, HttpStatus.CREATED);
    }
    // Ažuriranje (PUT) - identifikujemo zapis preko oba ID-ja
    @PutMapping("/radnik/{radnikId}/ekspozitura/{ekspoId}")
    public ResponseEntity<RadnikEkspoDTO> update(
            @PathVariable Integer radnikId,
            @PathVariable Integer ekspoId,
            @Valid @RequestBody CreateRadnikEkspoDTO dto) {

        // Kreiramo kompozitni ključ iz URL-a
        RadnikEkspoKey id = new RadnikEkspoKey(radnikId, ekspoId);
        RadnikEkspoDTO azuriranZapis = radnikEkspoService.update(id, dto);
        return ResponseEntity.ok(azuriranZapis);
    }

    // Brisanje (DELETE)
    @DeleteMapping("/radnik/{radnikId}/ekspozitura/{ekspoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer radnikId,
            @PathVariable Integer ekspoId) {

        RadnikEkspoKey id = new RadnikEkspoKey(radnikId, ekspoId);
        radnikEkspoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
