package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Estado;

import java.util.List;

public interface EstadoRepository extends CrudRepository <Estado,Long> {

    @Query("select e from Estado e")
    List<Estado> list();

    @Query("select e from Estado e where e.ibge = :parIbge")
    Estado findOneByIbge(String parIbge);
}

