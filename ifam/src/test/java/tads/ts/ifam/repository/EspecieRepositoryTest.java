package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.ts.ifam.model.Especie;

@DataJpaTest
public class EspecieRepositoryTest {

    @Autowired
    private EspecieRepository especieRepository ;


    @BeforeEach
    void preparaCondicao(){

        Especie especie = new Especie();

        especie.setNome("Camelus");
        especie.setDescricao("Camelos em Geral");

        especieRepository.save(especie);
    }

    @Test
    @DisplayName("Deve Salvar Nova Especie")
    public void deveSalvarNovaEspecie(){

        Especie especie = new Especie();

        especie.setNome("Bovina");
        especie.setDescricao("Boi em Geral");

        especieRepository.save(especie);

        Especie especieNoDB = especieRepository.findOneByNome(especie.getNome());

        Assertions.assertEquals(especie.getNome(), especieNoDB.getNome());
        Assertions.assertEquals(especie.getDescricao(), especieNoDB.getDescricao());

    }
    @Test
    @DisplayName("Nao Deve Salvar Especie Existente")
    public void naoDeveSalvarEspecieExistente(){

        Especie especie = new Especie();

        especie.setNome("Camelus");
        especie.setDescricao("Camelo em Geral");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {especieRepository.save(especie);});

    }
}
