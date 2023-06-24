package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Pet;

import java.util.List;

public interface PetRepository extends CrudRepository <Pet,Long> {

    @Query("select e from Pet e")
    List<Pet> list();

    @Query("select e from Pet e where e.nome = :nome")
    Pet findOneByNome(String nome);
}

