package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.ts.ifam.model.Cidade;
import tads.ts.ifam.model.Estado;
import tads.ts.ifam.model.Pessoa;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository ;


    @Autowired
    private EstadoRepository estadoRepository ;

    @Autowired
    private PessoaRepository pessoaRepository ;

    void preparaCondicao(){

        Cidade cidade1 = new Cidade();

        Estado estado1 = new Estado();

        Pessoa pessoa1 = new Pessoa();

        cidade1.setNome("Rio de Janeiro");
        cidade1.setIbge("RJ");
        cidade1.setEstado(estado1);

        cidadeRepository.save(cidade1);
    }

    @Test
    @DisplayName("Deve salvar com dados corretos")
    public void deveSalvarComDadosCorretos(){

        Cidade cidade1 = new Cidade();
        Estado estado1 = new Estado();
        Pessoa pessoa1 = new Pessoa();



        estado1.setNome("Rondonia");
        estado1.setSigla("RO");
        estado1.setIbge("3113");

        cidade1.setNome("PortoVelho");
        cidade1.setIbge("1331");
        cidade1.setEstado(estado1);

        pessoa1.setNome("Lucas");
        pessoa1.setEmail("lucas@gmail.com");
        pessoa1.setTelefone("9299999999");
        pessoa1.setCidade(cidade1);


        estadoRepository.save(estado1);

        cidadeRepository.save(cidade1);

        pessoaRepository.save(pessoa1);

        Pessoa pessoaNoDB = pessoaRepository.findOneByEmail(pessoa1.getEmail());

        Assertions.assertEquals(pessoa1.getEmail(), pessoaNoDB.getEmail());

    }
    @Test
    @DisplayName("Nao Deve salvar com dados corretos")
    public void naoDeveSalvarComDadosCorretos(){

        Cidade cidade1 = new Cidade();
        Estado estado1 = new Estado();

        cidade1.setNome("PortoVelho");
        cidade1.setIbge("1331");
        cidade1.setEstado(estado1);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {cidadeRepository.save(cidade1);});

    }
}
