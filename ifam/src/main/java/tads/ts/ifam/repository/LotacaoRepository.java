package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Lotacao;
import tads.ts.ifam.model.Pessoa;

import java.util.List;

public interface LotacaoRepository extends CrudRepository <Lotacao,Long> {

    @Query("select e from Lotacao e")
    List<Lotacao> list();

    @Query("select e from Lotacao e where e.nome = :nome")
    Lotacao findOneByNome(String nome);

    List<Lotacao> findByNomeContaining(String nome);
}

