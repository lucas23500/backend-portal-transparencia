package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Cidade;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.CidadeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> list(){
        return cidadeRepository.list();
    }





}

