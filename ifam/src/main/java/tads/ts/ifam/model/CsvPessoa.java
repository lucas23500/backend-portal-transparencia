package tads.ts.ifam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CsvPessoa {

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


    @Override
    public String toString() {
        return "CsvPessoa{" +
                "nome='" + nome + '\'' +
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


