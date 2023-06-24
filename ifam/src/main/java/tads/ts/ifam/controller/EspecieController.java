package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Especie;
import tads.ts.ifam.repository.EspecieRepository;

import java.util.List;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {

    @Autowired
    EspecieRepository especieRepository;

    @GetMapping
    public List<Especie> list(){
        return especieRepository.list();
    }





}

