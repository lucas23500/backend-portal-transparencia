package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Orgao;
import tads.ts.ifam.repository.OrgaoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/orgao")
public class OrgaoController {

    @Autowired
    OrgaoRepository orgaoRepository;

    @GetMapping
    public List<Orgao> list(){
        return orgaoRepository.list();
    }

    @GetMapping(value = "/search", params = "nome")
    public List<Orgao> search(@RequestParam("nome") String nome) {
        return orgaoRepository.findByNomeContaining(nome);
    }


    @GetMapping(value = "/search", params = "nomeCompleto")
    public Orgao searchByName(@RequestParam("nomeCompleto") String nome) {

        return orgaoRepository.findOneByNome(nome);
    }


}