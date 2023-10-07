package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Lotacao;
import tads.ts.ifam.model.Vinculo;

import java.util.List;

public interface VinculoRepository extends CrudRepository <Vinculo,Long> {

    @Query("select e from Vinculo e")
    List<Vinculo> list();

    @Query("select e from Vinculo e where e.nome = :nome")
    Vinculo findOneByNome(String nome);

    List<Vinculo> findByNomeContaining(String nome);
}

