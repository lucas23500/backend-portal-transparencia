package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.ts.ifam.model.Estado;

@DataJpaTest
public class EstadoRepositoryTest {

    @Autowired
    private EstadoRepository estadoRepository ;

    void preparaCondicao(){

        Estado estado1 = new Estado();


        estado1.setNome("Rio de Janeiro");
        estado1.setSigla("RJ");
        estado1.setIbge("35");

        estadoRepository.save(estado1);
    }

    @Test
    @DisplayName("Deve salvar com dados corretos")
    public void deveSalvarComDadosCorretos(){

        Estado estado1 = new Estado();

        estado1.setNome("Para");
        estado1.setSigla("PA");
        estado1.setIbge("55");

        estadoRepository.save(estado1);

        Estado estadoNoDB = estadoRepository.findOneByIbge(estado1.getIbge());

        Assertions.assertEquals(estado1.getIbge(), estadoNoDB.getIbge());

    }
    @Test
    @DisplayName("Nao Deve salvar com dados corretos")
    public void naoDeveSalvarComDadosCorretos(){

        Estado estado1 = new Estado();

        estado1.setNome("Rio de Janeiro");
        estado1.setSigla("RJ");
        estado1.setIbge("35");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {estadoRepository.save(estado1);});

    }
}
