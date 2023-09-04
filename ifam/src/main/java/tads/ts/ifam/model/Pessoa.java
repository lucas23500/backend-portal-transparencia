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
    private String remuneracaoTotal;
    private String descontoTeto;
    private String remunercaoDevida;
    private String descontosLegais;
    private String liquidosDisponiveis;

//    public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
//        List<Pessoa> findByRemuneracaoTotalGreaterThan(String remuneracaoTotal);
//    }
    public Pessoa(String nome, String lotacao, String cargo, String funcao, String vinculo, String remuneracaoTotal, String descontoTeto, String remunercaoDevida, String descontosLegais, String liquidosDisponiveis) {
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

    public String getRemuneracaoTotal() {
        return remuneracaoTotal;
    }

    public void setRemuneracaoTotal(String remuneracaoTotal) {
        this.remuneracaoTotal = remuneracaoTotal;
    }

    public String getDescontoTeto() {
        return descontoTeto;
    }

    public void setDescontoTeto(String descontoTeto) {
        this.descontoTeto = descontoTeto;
    }

    public String getRemunercaoDevida() {
        return remunercaoDevida;
    }

    public void setRemunercaoDevida(String remunercaoDevida) {
        this.remunercaoDevida = remunercaoDevida;
    }

    public String getDescontosLegais() {
        return descontosLegais;
    }

    public void setDescontosLegais(String descontosLegais) {
        this.descontosLegais = descontosLegais;
    }

    public String getLiquidosDisponiveis() {
        return liquidosDisponiveis;
    }

    public void setLiquidosDisponiveis(String liquidosDisponiveis) {
        this.liquidosDisponiveis = liquidosDisponiveis;
    }

    @Override
    public String toString() {
        return "CsvPessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", lotacao='" + lotacao + '\'' +
                ", cargo='" + cargo + '\'' +
                ", funcao='" + funcao + '\'' +
                ", vinculo='" + vinculo + '\'' +
                ", remuneracaoTotal='" + remuneracaoTotal + '\'' +
                ", descontoTeto='" + descontoTeto + '\'' +
                ", remunercaoDevida='" + remunercaoDevida + '\'' +
                ", descontosLegais='" + descontosLegais + '\'' +
                ", liquidosDisponiveis='" + liquidosDisponiveis + '\'' +
                '}';
    }
}

