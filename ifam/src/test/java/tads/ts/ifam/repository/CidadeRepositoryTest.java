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

        Estado estado1 = new Estado();

        estado1.setNome("Rio Grande do Sul");
        estado1.setSigla("RS");
        estado1.setIbge("99");

        estadoRepository.save(estado1);

        Cidade cidade1 = new Cidade();

        cidade1.setNome("Porto Alegre");
        cidade1.setIbge("98");
        cidade1.setEstado(estado1);

        cidadeRepository.save(cidade1);
    }

    @Test
    @DisplayName("Deve Salvar Nova Cidade")
    public void deveSalvarNovaCidade(){

        Estado estado1 = new Estado();

        estado1.setNome("Para");
        estado1.setSigla("PA");
        estado1.setIbge("80");

        estadoRepository.save(estado1);

        Cidade cidade1 = new Cidade();

        cidade1.setNome("Belém");
        cidade1.setIbge("81");
        cidade1.setEstado(estado1);

        cidadeRepository.save(cidade1);

        Cidade cidadeNoDB = cidadeRepository.findOneByIbge(cidade1.getIbge());

        Assertions.assertEquals(cidade1.getIbge(), cidadeNoDB.getIbge());
        Assertions.assertEquals(cidade1.getEstado(), cidadeNoDB.getEstado());
        Assertions.assertEquals(cidade1.getNome(), cidadeNoDB.getNome());
        Assertions.assertEquals(cidade1.getEstado().getNome(), cidadeNoDB.getEstado().getNome());
        Assertions.assertEquals(cidade1.getEstado().getIbge(), cidadeNoDB.getEstado().getIbge());
        Assertions.assertEquals(cidade1.getEstado().getSigla(), cidadeNoDB.getEstado().getSigla());
    }
    @Test
    @DisplayName("Nao Deve Salvar Cidade Existente")
    public void naoDeveSalvarCidadeExistente(){

        Cidade cidade1 = new Cidade();

        Estado estado1 = estadoRepository.findOneByIbge("99");

        cidade1.setNome("Porto Alegre");
        cidade1.setIbge("98");
        cidade1.setEstado(estado1);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {cidadeRepository.save(cidade1);});

    }
}
