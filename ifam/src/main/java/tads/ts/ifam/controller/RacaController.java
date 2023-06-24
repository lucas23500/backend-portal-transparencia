package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Raca;
import tads.ts.ifam.repository.RacaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/racas")
public class RacaController {

    @Autowired
    RacaRepository racaRepository;

    @GetMapping
    public List<Raca> list(){
        return racaRepository.list();
    }





}

