package tads.ts.ifam.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Pessoa {


    public Pessoa(String nome, Lotacao lotacao, Cargo cargo, Funcao funcao, Vinculo vinculo, Double remuneracaoTotal, String descontoTeto, Double remunercaoDevida, String descontosLegais, Double liquidosDisponiveis, Orgao orgaoDoGoverno, String mesPagamento, Integer anoPagamento) {
        this.nome = nome;
        this.lotacao = lotacao;
        this.cargo = cargo;
        this.funcao = funcao;
        this.vinculo = vinculo;
        this.remuneracaoTotal = remuneracaoTotal;
        this.descontoTeto = descontoTeto;
        this.remunercaoDevida = remunercaoDevida;
        this.descontosLegais = descontosLegais;
        this.liquidosDisponiveis = liquidosDisponiveis;
        this.orgaoDoGoverno = orgaoDoGoverno;
        this.mesPagamento = mesPagamento;
        this.anoPagamento = anoPagamento;
    }

    public Pessoa() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Lotacao lotacao;

    @ManyToOne
    private Cargo cargo;

    @ManyToOne
    private Funcao funcao;

    @ManyToOne
    private Vinculo vinculo;
    private Double remuneracaoTotal;
    private String descontoTeto;
    private Double remunercaoDevida;
    private String descontosLegais;
    private Double liquidosDisponiveis;
    @ManyToOne
    private Orgao orgaoDoGoverno;

    private String mesPagamento;
    private Integer anoPagamento;


    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", lotacao=" + lotacao.getNome() +
                ", cargo=" + cargo.getNome() +
                ", funcao=" + funcao.getNome() +
                ", vinculo=" + vinculo.getNome() +
                ", remuneracaoTotal=" + remuneracaoTotal +
                ", descontoTeto='" + descontoTeto + '\'' +
                ", remunercaoDevida=" + remunercaoDevida +
                ", descontosLegais='" + descontosLegais + '\'' +
                ", liquidosDisponiveis=" + liquidosDisponiveis +
                ", orgaoDoGoverno=" + orgaoDoGoverno.getNome() +
                ", mesPagamento='" + mesPagamento + '\'' +
                ", anoPagamento=" + anoPagamento +
                '}';
    }
}

