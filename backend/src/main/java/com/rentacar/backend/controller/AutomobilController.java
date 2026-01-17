package com.rentacar.backend.controller;


import com.rentacar.backend.dto.AutomobilDTO;
import com.rentacar.backend.dto.CreateAutomobilDTO;
import com.rentacar.backend.model.Automobil;
import com.rentacar.backend.services.AutomobilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automobili")
@RequiredArgsConstructor
@CrossOrigin( origins = "http://localhost:5173")
public class AutomobilController {

    private final AutomobilService automobilService;

    @GetMapping
    public ResponseEntity<List<AutomobilDTO>> getAll(){
        return ResponseEntity.ok(automobilService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomobilDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(automobilService.getAutomobilByID(id));
    }

    @PostMapping
    public ResponseEntity<AutomobilDTO> create(@Valid @RequestBody CreateAutomobilDTO dto) {
        // Servis prima CreateDTO, čuva i vraća AutomobilDTO
        AutomobilDTO sacuvan = automobilService.save(dto);
        return new ResponseEntity<>(sacuvan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        automobilService.deleteByID(id);
        return ResponseEntity.noContent().build(); // Status 204
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutomobilDTO> update(@PathVariable Integer id, @Valid @RequestBody CreateAutomobilDTO dto) {
        AutomobilDTO izmenjen = automobilService.update(id, dto);
        return ResponseEntity.ok(izmenjen);
    }
}
