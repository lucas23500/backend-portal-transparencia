package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Cargo;
import tads.ts.ifam.model.Funcao;
import tads.ts.ifam.repository.CargoRepository;
import tads.ts.ifam.repository.FuncaoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/funcao")
public class FuncaoController {

    @Autowired
    FuncaoRepository funcaoRepository;

    @GetMapping
    public List<Funcao> list(){
        return funcaoRepository.list();
    }

    @GetMapping(value = "/search", params = "nome")
    public List<Funcao> search(@RequestParam("nome") String nome) {
        return funcaoRepository.findByNomeContaining(nome);
    }


    @GetMapping(value = "/search", params = "nomeCompleto")
    public Funcao searchByName(@RequestParam("nomeCompleto") String nome) {

        return funcaoRepository.findOneByNome(nome);
    }


}