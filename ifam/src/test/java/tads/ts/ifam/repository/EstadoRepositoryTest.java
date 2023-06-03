package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void preparaCondicao(){

        Estado estado1 = new Estado();


        estado1.setNome("Rio Grande do Sul");
        estado1.setSigla("RS");
        estado1.setIbge("99");

        estadoRepository.save(estado1);
    }

    @Test
    @DisplayName("Deve salvar Novo Estado")
    public void deveSalvarNovoEstado(){

        Estado estado1 = new Estado();

        estado1.setNome("Para");
        estado1.setSigla("PA");
        estado1.setIbge("98");

        estadoRepository.save(estado1);

        Estado estadoNoDB = estadoRepository.findOneByIbge(estado1.getIbge());

        Assertions.assertEquals(estado1.getIbge(), estadoNoDB.getIbge());
        Assertions.assertEquals(estado1.getSigla(), estadoNoDB.getSigla());
        Assertions.assertEquals(estado1.getNome(), estadoNoDB.getNome());

    }
    @Test
    @DisplayName("Nao Deve Salvar Estado Existente")
    public void naoDeveSalvarEstadoExistente(){

        Estado estado1 = new Estado();

        estado1.setNome("Rio Grande do Sul");
        estado1.setSigla("RS");
        estado1.setIbge("99");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {estadoRepository.save(estado1);});

    }
}
