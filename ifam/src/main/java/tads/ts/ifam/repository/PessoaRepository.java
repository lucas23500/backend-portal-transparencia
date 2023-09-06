package tads.ts.ifam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.ts.ifam.model.Pessoa;

import java.math.BigDecimal;
import java.util.List;

public interface PessoaRepository extends CrudRepository <Pessoa,Long> {

    @Query("select e from Pessoa e")
    List<Pessoa> list();

    @Query("select e from Pessoa e where e.nome = :nome")
    Pessoa findOneByNome(String nome);

    List<Pessoa> findByNomeContaining(String nome);
    List<Pessoa> findByOrgaoDoGovernoContaining(String OrgaoDoGoverno);

    @Query("SELECT p FROM Pessoa p WHERE p.remuneracaoTotal > :salario")
    List<Pessoa> findByRemuneracaoTotalGreaterThan(Double salario);
}

