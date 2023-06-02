package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Cidade;

import java.util.List;

public interface CidadeRepository extends CrudRepository <Cidade,Long> {

    @Query("select e from Cidade e")
    List<Cidade> list();

    @Query("select e from Cidade e where e.ibge = :parIbge")
    Cidade findOneByIbge(String parIbge);
}

