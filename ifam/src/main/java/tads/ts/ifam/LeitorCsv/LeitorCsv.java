package tads.ts.ifam.LeitorCsv;

import tads.ts.ifam.model.CsvPessoa;
import tads.ts.ifam.model.Pessoa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {

        public List<Pessoa> lerCsv(String endereco) throws IOException {

            List<CsvPessoa> pessoas = new ArrayList<>();

            Files.lines(Paths.get(endereco))
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(col-> new CsvPessoa(col[0],col[1],col[2],col[3],col[4],col[5],col[6],col[7],col[8],col[9]))
                    .forEach(pessoas::add);

            List<Pessoa> novaPessoas = converteCsvPessoa(pessoas);

            return novaPessoas;
        }

        private List<Pessoa> converteCsvPessoa(List<CsvPessoa> pessoas){
            List<Pessoa> uni = new ArrayList<>();

            for(CsvPessoa pessoa: pessoas){
            Pessoa novaPessoa = new Pessoa(
                    pessoa.getNome(),
                    pessoa.getLotacao(),
                    pessoa.getCargo(),
                    pessoa.getFuncao(),
                    pessoa.getVinculo(),
                    pessoa.getRemuneracaoTotal(),
                    pessoa.getDescontoTeto(),
                    pessoa.getRemunercaoDevida(),
                    pessoa.getDescontosLegais(),
                    pessoa.getLiquidosDisponiveis()
            );
            uni.add(novaPessoa);

            }
            return uni;
        }
}