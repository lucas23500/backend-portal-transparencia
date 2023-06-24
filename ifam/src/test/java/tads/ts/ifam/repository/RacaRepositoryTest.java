package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.ts.ifam.model.Especie;
import tads.ts.ifam.model.Raca;

@DataJpaTest
public class RacaRepositoryTest {

    @Autowired
    private EspecieRepository especieRepository ;

    @Autowired
    private RacaRepository racaRepository ;


    @BeforeEach
    void preparaCondicao(){

        Especie especie = new Especie();

        especie.setNome("Felina");
        especie.setDescricao("Gatos em Geral");

        especieRepository.save(especie);

        Raca raca = new Raca();

        raca.setNome("Siames");
        raca.setDescricao("Gato Oriental");
        raca.setEspecie(especie);

        racaRepository.save(raca);
    }

    @Test
    @DisplayName("Deve Salvar Nova Raca")
    public void deveSalvarNovaRaca(){

        Especie especie = new Especie();

        especie.setNome("Bovina");
        especie.setDescricao("Boi em Geral");

        especieRepository.save(especie);

        Raca raca = new Raca();

        raca.setNome("Bos Tauros");
        raca.setDescricao("Gado Bovino");
        raca.setEspecie(especie);

        racaRepository.save(raca);

        Raca racaNoDB = racaRepository.findOneByNome(raca.getNome());

        Assertions.assertEquals(raca.getNome(), racaNoDB.getNome());
        Assertions.assertEquals(raca.getDescricao(), racaNoDB.getDescricao());
        Assertions.assertEquals(raca.getEspecie(), racaNoDB.getEspecie());

    }
    @Test
    @DisplayName("Nao Deve Salvar Especie Existente")
    public void naoDeveSalvarEspecieExistente(){

        Especie especie = new Especie();

        especie.setNome("Felina");
        especie.setDescricao("Gatos em Geral");

        Raca raca = new Raca();

        raca.setNome("Siames");
        raca.setDescricao("Gato Oriental");
        raca.setEspecie(especie);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {racaRepository.save(raca);});

    }
}
