package tads.ts.ifam.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String nome;

    public Cargo(String nome) {
        this.nome = nome;
    }

    public Cargo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cargo() {
    }
}
