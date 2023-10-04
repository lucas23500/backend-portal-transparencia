package tads.ts.ifam.LeitorCsv;

import lombok.extern.slf4j.Slf4j;
import tads.ts.ifam.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class LeitorCsv {

    private final String endereco = "C:/dev/projetos/tcc-projeto/atividadeGPS/ifam/src/main/resources/dados/";

    public List<String> listarNomesArquivosCSV() {
        List<String> nomesArquivosCSV = new ArrayList<>();

        File diretorio = new File(this.endereco);

        // Verifica se o diretório existe
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivosCSV = diretorio.listFiles((dir, nome) -> nome.endsWith(".csv"));

            if (arquivosCSV != null) {
                for (File arquivoCSV : arquivosCSV) {
                    nomesArquivosCSV.add(arquivoCSV.getName());
                }
            } else {
                System.out.println("Nenhum arquivo CSV encontrado na pasta.");
            }
        } else {
            System.out.println("A pasta não existe ou não é um diretório.");
        }

        return nomesArquivosCSV;
    }
    public List<Pessoa> lerCsv(String endereco) throws IOException {
        List<CsvPessoa> pessoas = new ArrayList<>();


        Files.lines(Paths.get(this.endereco + endereco), StandardCharsets.UTF_8)
                .skip(1)
                .map(line -> line.split(";", -1)) // Usar -1 para manter campos vazios
                .map(col -> {
                    if (col.length >= 10) {
                        return new CsvPessoa(col[0], col[1], col[2], col[3], col[4], col[5], col[6], col[7], col[8], col[9]);
                    } else {
                        // Lide com o caso em que a linha não tem todas as colunas esperadas
                        // Você pode registrar um erro ou fazer o tratamento adequado aqui
                        log.error("Erro ao ler linha: " + String.join(";", col));
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Remova linhas com erro
                .forEach(pessoas::add);




//                .map(col -> new CsvPessoa(col[0], col[1], col[2], col[3], col[4], col[5], col[6], col[7], col[8], col[9]))
//                .forEach(pessoas::add);

        List<Pessoa> novaPessoas = new ArrayList<>();
        try {
            novaPessoas = converteCsvPessoa(
                    pessoas,
                    endereco.replaceAll("^(\\d+)_.*$", "$1"),
                    endereco.replaceAll(".*_(\\d{4})(\\d{2})\\.csv", "$2"),
                    Integer.valueOf(endereco.replaceAll(".*_(\\d{4})\\d{2}\\.csv", "$1")));

        }catch (NumberFormatException e){
            log.info("Exception problema no arquivo : " + endereco);
        }
        return novaPessoas;
    }


    private List<Pessoa> converteCsvPessoa(List<CsvPessoa> pessoas, String orgaoDoGoverno, String mesPagamento, Integer anoPagamento){
        List<Pessoa> uni = new ArrayList<>();

        for(CsvPessoa pessoa: pessoas) {

            // AQUI TUDO QUE TEM PONTO VIRA VIRGULA
            var remuneracaoTotal = pessoa.getRemuneracaoTotal().replaceAll("\\.", ",");
            var remuneracaoDevida = pessoa.getRemunercaoDevida().replaceAll("\\.", ",");
            var liquidosDisponiveis = pessoa.getLiquidosDisponiveis().replaceAll("\\.", ",");

            double rmnT = 0.0;
            double rmnD = 0.0;
            double LD = 0.0;

            String rmnTotal = null;
            String rmnDevida = null;
            String liquidosDis = null;

            try { //TENTANDO REMOVER A VIRGULA
                rmnTotal = removeVirgulasEsquerda(remuneracaoTotal);
                rmnDevida = removeVirgulasEsquerda(remuneracaoDevida);
                liquidosDis = removeVirgulasEsquerda(liquidosDisponiveis);

            } catch (NumberFormatException e) {
                log.info("Exception problema no arquivo: " + pessoa.getRemuneracaoTotal());
                var remuneracaTotal = Double.parseDouble(removeVirgulasEsquerda(pessoa.getRemuneracaoTotal().replaceAll("\\.", ",")).replaceAll(",", "."));

            }


            try { //TENTANDO
                rmnT = Double.parseDouble(rmnTotal);
                rmnD = Double.parseDouble(rmnDevida);
                LD = Double.parseDouble(liquidosDis);

            } catch (NumberFormatException e) {
                log.info("Exception problema na remuneracaoTotal: " + rmnTotal);
                log.info("Exception problema na remuneracaoDevida: " + rmnDevida);
                log.info("Exception problema na liquidosDis: " + liquidosDis);

            }

            Pessoa novaPessoa = new Pessoa(
                    pessoa.getNome(),
                    pessoa.getLotacao(),
                    pessoa.getCargo(),
                    pessoa.getFuncao(),
                    pessoa.getVinculo(),
                    rmnT,
                    pessoa.getDescontoTeto(),
                    rmnD,
                    pessoa.getDescontosLegais(),
                    LD,
                    OrgaoEnum.orgaoEnum(Integer.parseInt(orgaoDoGoverno)),
                    MesEnum.mesEnum(Integer.parseInt(mesPagamento)),
                    anoPagamento
            );
            uni.add(novaPessoa);

        }
        return uni;
    }


    public static String removeVirgulasEsquerda(String input) {



        input = input.replaceAll(",", "");
        input = input.replaceAll("\\.", "");
        String segundaParte = null;
        String primeiraParte = null;
        try {
            primeiraParte = input.substring(0, input.length() - 2);
            segundaParte = input.substring(input.length() - 2);
        }catch (StringIndexOutOfBoundsException e ){
            System.out.println("Erro no input: " + input);
        }
        String res = primeiraParte + "." + segundaParte;
        return res;

    }

}