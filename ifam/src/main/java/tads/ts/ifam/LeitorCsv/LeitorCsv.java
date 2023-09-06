package tads.ts.ifam.LeitorCsv;

import tads.ts.ifam.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
                .map(line -> line.split(";"))
                .map(col-> new CsvPessoa(col[0],col[1],col[2],col[3],col[4],col[5],col[6],col[7],col[8],col[9]))
                .forEach(pessoas::add);

        List<Pessoa> novaPessoas = converteCsvPessoa(
                pessoas,
                endereco.replaceAll("^(\\d+)_.*$", "$1"),
                endereco.replaceAll(".*_(\\d{4})(\\d{2})\\.csv", "$2"),
                Integer.valueOf(endereco.replaceAll(".*_(\\d{4})\\d{2}\\.csv", "$1")));

        return novaPessoas;
    }


    private List<Pessoa> converteCsvPessoa(List<CsvPessoa> pessoas, String orgaoDoGoverno, String mesPagamento, Integer anoPagamento){
        List<Pessoa> uni = new ArrayList<>();

        for(CsvPessoa pessoa: pessoas){
        Pessoa novaPessoa = new Pessoa(
                pessoa.getNome(),
                pessoa.getLotacao(),
                pessoa.getCargo(),
                pessoa.getFuncao(),
                pessoa.getVinculo(),
                Double.parseDouble(pessoa.getRemuneracaoTotal().replaceAll(",",".")),
                pessoa.getDescontoTeto(),
                Double.parseDouble(pessoa.getRemunercaoDevida().replaceAll(",",".")),
                pessoa.getDescontosLegais(),
                Double.parseDouble(pessoa.getLiquidosDisponiveis().replaceAll(",",".")),
                OrgaoEnum.orgaoEnum(Integer.parseInt(orgaoDoGoverno)),
                MesEnum.mesEnum(Integer.parseInt(mesPagamento)),
                anoPagamento
        );
        uni.add(novaPessoa);

        }
        return uni;
    }
}