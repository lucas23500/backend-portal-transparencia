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

    @GetMapping(value = "/search", params = "nome")
    public List<Pessoa> search(@RequestParam("nome") String nome) {
        return pessoaRepository.findByNomeContaining(nome);
    }

    @GetMapping(value = "/search", params = "maiorSalario")
    public List<Pessoa> searchBySalary(@RequestParam("maiorSalario") Double salario) {
        return pessoaRepository.findByRemuneracaoTotalGreaterThan(salario);
    }

    @GetMapping(value = "/search", params = "orgao")
    public List<Pessoa> searchByOrgao(@RequestParam("orgao") String orgao) {
        return pessoaRepository.findByOrgaoDoGovernoContaining(orgao);
    }

}