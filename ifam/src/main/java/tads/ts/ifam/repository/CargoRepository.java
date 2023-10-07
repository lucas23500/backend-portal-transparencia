package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Cargo;
import tads.ts.ifam.model.Lotacao;

import java.util.List;

public interface CargoRepository extends CrudRepository <Cargo,Long> {

    @Query("select e from Cargo e")
    List<Cargo> list();

    @Query("select e from Cargo e where e.nome = :nome")
    Cargo findOneByNome(String nome);

    List<Cargo> findByNomeContaining(String nome);
}

