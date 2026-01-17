package com.rentacar.backend.controller;


import com.rentacar.backend.dto.CreateGradDTO;
import com.rentacar.backend.dto.GradDTO;
import com.rentacar.backend.model.Grad;
import com.rentacar.backend.services.GradService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gradovi")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class GradController {
    private final GradService gradService;


    @GetMapping
    public ResponseEntity<List<GradDTO>> getAll() {
        return ResponseEntity.ok(gradService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(gradService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GradDTO> create(@Valid @RequestBody CreateGradDTO dto) {
        return new ResponseEntity<>(gradService.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        gradService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
