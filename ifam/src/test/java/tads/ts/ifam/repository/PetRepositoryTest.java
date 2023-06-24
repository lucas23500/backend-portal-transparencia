package tads.ts.ifam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.ts.ifam.model.Especie;
import tads.ts.ifam.model.Pet;
import tads.ts.ifam.model.Raca;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private EspecieRepository especieRepository ;

    @Autowired
    private RacaRepository racaRepository ;

    @Autowired
    private PetRepository petRepository ;


    @BeforeEach
    void preparaCondicao() throws Exception {

        Especie especie = new Especie();

        especie.setNome("Felina");
        especie.setDescricao("Gatos em Geral");

        especieRepository.save(especie);

        Raca raca = new Raca();

        raca.setNome("Siames");
        raca.setDescricao("Gato Oriental");
        raca.setEspecie(especie);

        racaRepository.save(raca);

        Pet pet = new Pet();

        pet.setNome("Layla");
        pet.setChip("123456789012345");
        pet.setIdade(13);
        pet.setRaca(raca);

        petRepository.save(pet);
    }

    @Test
    @DisplayName("Deve Salvar Nova Pet")
    public void deveSalvarNovaPet() throws Exception {

        Especie especie = new Especie();

        especie.setNome("Bovina");
        especie.setDescricao("Boi em Geral");

        especieRepository.save(especie);

        Raca raca = new Raca();

        raca.setNome("Bos Tauros");
        raca.setDescricao("Gado Bovino");
        raca.setEspecie(especie);

        racaRepository.save(raca);

        Pet pet = new Pet();

        pet.setNome("Nick");
        pet.setChip("000000000000000");
        pet.setIdade(13);
        pet.setRaca(raca);

        petRepository.save(pet);

        Pet petNoDB = petRepository.findOneByNome(pet.getNome());

        Assertions.assertEquals(pet.getNome(), petNoDB.getNome());
        Assertions.assertEquals(pet.getIdade(), petNoDB.getIdade());
        Assertions.assertEquals(pet.getRaca(), petNoDB.getRaca());

    }
    @Test
    @DisplayName("Nao Deve Salvar Pet Existente")
    public void naoDeveSalvarPetExistente() throws Exception {

        Especie especie = new Especie();

        especie.setNome("Felina");
        especie.setDescricao("Gatos em Geral");

        Raca raca = new Raca();

        raca.setNome("Siames");
        raca.setDescricao("Gato Oriental");
        raca.setEspecie(especie);

        Pet pet = new Pet();

        pet.setNome("Layla");
        pet.setChip("123456789012345");
        pet.setIdade(13);
        pet.setRaca(raca);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {petRepository.save(pet);});

    }

    @Test
    @DisplayName("Nao Deve Salvar Pet Idade Maior")
    public void naoDeveSalvarPetIdadeMaior() throws Exception {

        Pet pet = new Pet();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {pet.setIdade(14);;});

    }



    @Test
    @DisplayName("Nao Deve Salvar Pet Idade Menor")
    public void naoDeveSalvarPetIdadeMenor() throws Exception {

        Pet pet = new Pet();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {pet.setIdade(-1);;});

    }

    @Test
    @DisplayName("Nao Deve Salvar Pet Chip Maior")
    public void naoDeveSalvarPetChipMaior() throws Exception {

        Pet pet = new Pet();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {pet.setChip("1324567890123456789123");;});

    }

    @Test
    @DisplayName("Nao Deve Salvar Pet Chip Menor")
    public void naoDeveSalvarPetChipMenor() throws Exception {

        Pet pet = new Pet();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {pet.setChip("1");;});

    }

    @Test
    @DisplayName("Nao Deve Salvar Pet Informacoes Incompletas")
    public void naoDeveSalvarPetIncompleto() throws Exception {

        Pet pet = new Pet();
        pet.setNome("incompleto");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {petRepository.save(pet);});

    }
}
