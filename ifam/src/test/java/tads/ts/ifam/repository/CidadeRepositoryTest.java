package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.ts.ifam.model.Cidade;
import tads.ts.ifam.model.Estado;

@DataJpaTest
public class CidadeRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository ;


    @Autowired
    private EstadoRepository estadoRepository ;

    @BeforeEach
    void preparaCondicao(){

        Cidade cidade1 = new Cidade();

        Estado estado1 = new Estado();

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



        estado1.setNome("Rondonia");
        estado1.setSigla("RO");
        estado1.setIbge("3113");

        cidade1.setNome("PortoVelho");
        cidade1.setIbge("1331");
        cidade1.setEstado(estado1);


        estadoRepository.save(estado1);

        cidadeRepository.save(cidade1);

        Cidade cidadeNoDB = cidadeRepository.findOneByIbge(cidade1.getIbge());

        Assertions.assertEquals(cidade1.getIbge(), cidadeNoDB.getIbge());

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
