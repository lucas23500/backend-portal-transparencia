package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Cargo;
import tads.ts.ifam.model.Lotacao;
import tads.ts.ifam.repository.CargoRepository;
import tads.ts.ifam.repository.LotacaoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    @Autowired
    CargoRepository cargoRepository;

    @GetMapping
    public List<Cargo> list(){
        return cargoRepository.list();
    }

    @GetMapping(value = "/search", params = "nome")
    public List<Cargo> search(@RequestParam("nome") String nome) {
        return cargoRepository.findByNomeContaining(nome);
    }


    @GetMapping(value = "/search", params = "nomeCompleto")
    public Cargo searchByName(@RequestParam("nomeCompleto") String nome) {

        return cargoRepository.findOneByNome(nome);
    }


}