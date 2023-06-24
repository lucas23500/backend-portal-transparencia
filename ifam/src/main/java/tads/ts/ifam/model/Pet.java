package tads.ts.ifam.model;

import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String chip;

    @Column(nullable = false)
    private Integer idade;

    @ManyToOne
    private Raca raca;

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) throws Exception {
        if(chip.length() != 15){
            throw new DataIntegrityViolationException("Idade não permitida");
        }
        this.chip = chip;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) throws Exception {
        if(idade > 13 || idade < 0){
            throw new DataIntegrityViolationException("Idade não permitida");
        }
        this.idade = idade;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }
}
