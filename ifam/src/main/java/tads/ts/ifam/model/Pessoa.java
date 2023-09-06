package tads.ts.ifam.model;


import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public Double getRemuneracaoTotal() {
        return remuneracaoTotal;
    }

    public void setRemuneracaoTotal(Double remuneracaoTotal) {
        this.remuneracaoTotal = remuneracaoTotal;
    }

    public String getDescontoTeto() {
        return descontoTeto;
    }

    public void setDescontoTeto(String descontoTeto) {
        this.descontoTeto = descontoTeto;
    }

    public Double getRemunercaoDevida() {
        return remunercaoDevida;
    }

    public void setRemunercaoDevida(Double remunercaoDevida) {
        this.remunercaoDevida = remunercaoDevida;
    }

    public String getDescontosLegais() {
        return descontosLegais;
    }

    public void setDescontosLegais(String descontosLegais) {
        this.descontosLegais = descontosLegais;
    }

    public Double getLiquidosDisponiveis() {
        return liquidosDisponiveis;
    }

    public void setLiquidosDisponiveis(Double liquidosDisponiveis) {
        this.liquidosDisponiveis = liquidosDisponiveis;
    }

    public String getOrgaoDoGoverno() {
        return orgaoDoGoverno;
    }

    public void setOrgaoDoGoverno(String orgaoDoGoverno) {
        this.orgaoDoGoverno = orgaoDoGoverno;
    }

    public String getMesPagamento() {
        return mesPagamento;
    }

    public void setMesPagamento(String mesPagamento) {
        this.mesPagamento = mesPagamento;
    }

    public Integer getAnoPagamento() {
        return anoPagamento;
    }

    public void setAnoPagamento(Integer anoPagamento) {
        this.anoPagamento = anoPagamento;
    }
}

