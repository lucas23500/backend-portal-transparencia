package tads.ts.ifam.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Orgao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String nome;

    public Orgao(String nome) {
        this.nome = nome;
    }

    public Orgao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Orgao() {
    }
}
