package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Raca;

import java.util.List;

public interface RacaRepository extends CrudRepository <Raca,Long> {

    @Query("select e from Raca e")
    List<Raca> list();

    @Query("select e from Raca e where e.nome = :nome")
    Raca findOneByNome(String nome);
}

