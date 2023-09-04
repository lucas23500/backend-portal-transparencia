package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.CsvPessoa;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.PessoaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> list(){
        return pessoaRepository.list();
    }

    @GetMapping("/search")
    public List<Pessoa> search(@RequestParam("nome") String nome) {
        return pessoaRepository.findByNomeContaining(nome);
    }

//    @GetMapping("/search")
//    public List<Pessoa> searchSalario(@RequestParam("remuneracaoTotal") String remuneracaoTotal) {
//
//        return pessoaRepository.findByRemuneracaoTotalContaining(remuneracaoTotal);
//    }









}