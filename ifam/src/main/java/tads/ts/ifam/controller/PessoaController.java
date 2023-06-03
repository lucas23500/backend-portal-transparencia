package tads.ts.ifam.controller;

import org.hibernate.collection.internal.PersistentSortedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.ts.ifam.model.Estado;
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


    @GetMapping("/email/{email}")
    public Pessoa getByEmail(@PathVariable("email") String email){
        return pessoaRepository.findOneByEmail(email);
    }

    @GetMapping("/{id}")
    public Pessoa getById(@PathVariable("id") Long id){
        return pessoaRepository.findOneById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return ResponseEntity.status(200).build();
    }

}

