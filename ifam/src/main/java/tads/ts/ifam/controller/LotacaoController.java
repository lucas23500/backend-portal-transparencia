package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Lotacao;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.LotacaoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/lotacao")
public class LotacaoController {

    @Autowired
    LotacaoRepository lotacaoRepository;

    @GetMapping
    public List<Lotacao> list(){
        return lotacaoRepository.list();
    }

    @GetMapping(value = "/search", params = "nome")
    public List<Lotacao> search(@RequestParam("nome") String nome) {
        return lotacaoRepository.findByNomeContaining(nome);
    }


    @GetMapping(value = "/search", params = "nomeCompleto")
    public Lotacao searchByName(@RequestParam("nomeCompleto") String nome) {

        return lotacaoRepository.findOneByNome(nome);
    }


}