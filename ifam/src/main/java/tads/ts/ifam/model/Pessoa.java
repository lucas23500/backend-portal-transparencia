package tads.ts.ifam.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Pessoa {

    public Pessoa(String nome, String lotacao, String cargo, String funcao, String vinculo, Double remuneracaoTotal, String descontoTeto, Double remunercaoDevida, String descontosLegais, Double liquidosDisponiveis, String orgaoDoGoverno, String mesPagamento, Integer anoPagamento) {
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

    private String lotacao;
    private String cargo;
    private String funcao;
    private String vinculo;
    private Double remuneracaoTotal;
    private String descontoTeto;
    private Double remunercaoDevida;
    private String descontosLegais;
    private Double liquidosDisponiveis;

    private String orgaoDoGoverno;

    private String mesPagamento;
    private Integer anoPagamento;
}

