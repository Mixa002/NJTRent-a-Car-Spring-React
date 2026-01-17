package com.rentacar.backend.controller;


import com.rentacar.backend.dto.StavkaIznajmljivanjaDTO;
import com.rentacar.backend.model.RadnikEkspo;
import com.rentacar.backend.model.StavkaIznajmljivanja;
import com.rentacar.backend.services.RadnikEkspoService;
import com.rentacar.backend.services.StavkaIznajmljivanjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stavke-iznajmljivanja")
@CrossOrigin(origins = "http://localhost:5173")
public class StavkaIznajmljivanjaController {
    private final StavkaIznajmljivanjaService stavkaIznajmljivanjaService;

    public StavkaIznajmljivanjaController(StavkaIznajmljivanjaService stavkaIznajmljivanjaService) {
        this.stavkaIznajmljivanjaService = stavkaIznajmljivanjaService;
    }

    @GetMapping
    public List<StavkaIznajmljivanjaDTO> getAll(){
        return stavkaIznajmljivanjaService.getAll();
    }

//    @GetMapping("/{id}")
//    public StavkaIznajmljivanjaDTO getById(@PathVariable Integer id){
//        return stavkaIznajmljivanjaService.ge(id);
//    }
//
//    @PostMapping
//    public StavkaIznajmljivanja create(@RequestBody StavkaIznajmljivanja stavkaIznajmljivanja){
//        return stavkaIznajmljivanjaService.save(stavkaIznajmljivanja);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id){
//        stavkaIznajmljivanjaService.deleteById(id);
//    }
}
