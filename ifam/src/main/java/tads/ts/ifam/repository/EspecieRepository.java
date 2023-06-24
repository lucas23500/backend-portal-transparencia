package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Especie;

import java.util.List;

public interface EspecieRepository extends CrudRepository <Especie,Long> {

    @Query("select e from Especie e")
    List<Especie> list();

    @Query("select e from Especie e where e.nome = :nome")
    Especie findOneByNome(String nome);
}

