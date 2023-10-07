package tads.ts.ifam.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String nome;

    public Funcao(String nome) {
        this.nome = nome;
    }

    public Funcao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Funcao() {
    }
}
