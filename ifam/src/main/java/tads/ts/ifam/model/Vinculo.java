package tads.ts.ifam.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Vinculo {

    // Variável estática para rastrear o próximo ID disponível
    private static Long proximoId = 100L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    public Vinculo(String nome) {
        this.id = proximoId++; // Atribui o próximo ID disponível e o incrementa
        this.nome = nome;
    }

    public Vinculo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Vinculo() {
    }
}
