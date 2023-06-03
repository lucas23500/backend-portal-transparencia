package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Pessoa;

import java.util.List;

public interface PessoaRepository extends CrudRepository <Pessoa,Long> {

    @Query("select e from Pessoa e")
    List<Pessoa> list();

    @Query("select e from Pessoa e where e.email = :parEmail")
    Pessoa findOneByEmail(String parEmail);

    @Query("select e from Pessoa e where e.id = :parId")
    Pessoa findOneById(Long parId);

}

