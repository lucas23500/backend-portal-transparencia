package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.ts.ifam.model.Cidade;
import tads.ts.ifam.model.Estado;
import tads.ts.ifam.repository.EstadoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> list(){
        return estadoRepository.list();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Estado estado){
        estadoRepository.save(estado);
        return ResponseEntity.status(201).build();
    }





}

