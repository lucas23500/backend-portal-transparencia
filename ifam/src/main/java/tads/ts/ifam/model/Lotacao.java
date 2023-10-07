package tads.ts.ifam.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String nome;

    public Lotacao(String nome) {
        this.nome = nome;
    }

    public Lotacao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Lotacao() {
    }
}
