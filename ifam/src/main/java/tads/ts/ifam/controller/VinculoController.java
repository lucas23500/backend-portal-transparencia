package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Vinculo;
import tads.ts.ifam.repository.VinculoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/vinculo")
public class VinculoController {

    @Autowired
    VinculoRepository vinculoRepository;

    @GetMapping
    public List<Vinculo> list(){
        return vinculoRepository.list();
    }

    @GetMapping(value = "/search", params = "nome")
    public List<Vinculo> search(@RequestParam("nome") String nome) {
        return vinculoRepository.findByNomeContaining(nome);
    }


    @GetMapping(value = "/search", params = "nomeCompleto")
    public Vinculo searchByName(@RequestParam("nomeCompleto") String nome) {

        return vinculoRepository.findOneByNome(nome);
    }


}