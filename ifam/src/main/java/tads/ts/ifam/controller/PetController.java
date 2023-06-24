package tads.ts.ifam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tads.ts.ifam.model.Pet;
import tads.ts.ifam.repository.PetRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    PetRepository petRepository;

    @GetMapping
    public List<Pet> list(){
        return petRepository.list();
    }





}

