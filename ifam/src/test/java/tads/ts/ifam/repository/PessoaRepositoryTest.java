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
import tads.ts.ifam.model.Pessoa;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository ;

    @Autowired
    private EstadoRepository estadoRepository ;

    @Autowired
    private PessoaRepository pessoaRepository ;

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

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Rogerio");
        pessoa1.setEmail("Rogerio@gmail.com");
        pessoa1.setTelefone("92999000999");
        pessoa1.setCidade(cidade1);

        pessoaRepository.save(pessoa1);
    }

    @Test
    @DisplayName("Deve salvar Nova Pessoa")
    public void deveSalvarNovaPessoa(){

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

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Fernando Monteiro");
        pessoa1.setEmail("fernando@gmail.com");
        pessoa1.setTelefone("92111000111");
        pessoa1.setCidade(cidade1);

        pessoaRepository.save(pessoa1);

        Pessoa pessoaNoDB = pessoaRepository.findOneByEmail(pessoa1.getEmail());

        Assertions.assertEquals(pessoa1.getEmail(), pessoaNoDB.getEmail());
        Assertions.assertEquals(pessoa1.getNome(), pessoaNoDB.getNome());
        Assertions.assertEquals(pessoa1.getTelefone(), pessoaNoDB.getTelefone());
        Assertions.assertEquals(pessoa1.getCidade().getNome(), pessoaNoDB.getCidade().getNome());
        Assertions.assertEquals(pessoa1.getCidade().getIbge(), pessoaNoDB.getCidade().getIbge());
        Assertions.assertEquals(pessoa1.getCidade().getEstado().getNome(), pessoaNoDB.getCidade().getEstado().getNome());
        Assertions.assertEquals(pessoa1.getCidade().getEstado().getSigla(), pessoaNoDB.getCidade().getEstado().getSigla());
        Assertions.assertEquals(pessoa1.getCidade().getEstado().getIbge(), pessoaNoDB.getCidade().getEstado().getIbge());
    }
    @Test
    @DisplayName("Nao Deve salvar Pessoa Existente")
    public void naoDeveSalvarPessoaExistente(){

        Cidade cidade1 = cidadeRepository.findOneByIbge("98");

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Rogerio");
        pessoa1.setEmail("Rogerio@gmail.com");
        pessoa1.setTelefone("92999000999");
        pessoa1.setCidade(cidade1);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {pessoaRepository.save(pessoa1);});
    }
}
