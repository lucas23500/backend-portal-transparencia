package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Orgao;

import java.util.List;

public interface OrgaoRepository extends CrudRepository <Orgao,Long> {

    @Query("select e from Orgao e")
    List<Orgao> list();

    @Query("select e from Orgao e where e.nome = :nome")
    Orgao findOneByNome(String nome);

    List<Orgao> findByNomeContaining(String nome);
}

