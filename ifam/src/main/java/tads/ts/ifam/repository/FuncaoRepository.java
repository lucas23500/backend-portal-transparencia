package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Funcao;
import tads.ts.ifam.model.Lotacao;

import java.util.List;

public interface FuncaoRepository extends CrudRepository <Funcao,Long> {

    @Query("select e from Funcao e")
    List<Funcao> list();

    @Query("select e from Funcao e where e.nome = :nome")
    Funcao findOneByNome(String nome);

    List<Funcao> findByNomeContaining(String nome);
}

