package tads.ts.ifam.LeitorCsv;

import lombok.extern.slf4j.Slf4j;
import tads.ts.ifam.model.MesEnum;
import tads.ts.ifam.model.OrgaoEnum;
import tads.ts.ifam.model.Pessoa;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class LeitorCsv {

    private final String endereco = "C:/dev/projetos/tcc-projeto/backend-portal-transparencia/ifam/src/main/resources/dados/";

    public List<String> listarNomesArquivosCSV() {
        List<String> nomesArquivosCSV = new ArrayList<>();

        Path diretorio = Paths.get(this.endereco);

        if (Files.exists(diretorio) && Files.isDirectory(diretorio)) {
            try {
                Files.list(diretorio)
                        .filter(path -> path.toString().endsWith(".csv"))
                        .forEach(path -> nomesArquivosCSV.add(path.getFileName().toString()));
            } catch (IOException e) {
                log.error("Erro ao listar arquivos CSV", e);
            }
        } else {
            log.error("A pasta não existe ou não é um diretório.");
        }

        return nomesArquivosCSV;
    }

    public List<Pessoa> lerCsv(String endereco) throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();

        try {
            Files.lines(Paths.get(this.endereco + endereco), StandardCharsets.UTF_8)
                    .skip(1)
                    .map(line -> line.split(";", -1)) // Usar -1 para manter campos vazios
                    .filter(col -> col.length >= 10)
                    .map(col -> {
                        try {
                            String remuneracaoTotal = col[5].replace(".", "").replace(",", ".");
                            String remuneracaoDevida = col[7].replace(".", "").replace(",", ".");
                            String liquidosDisponiveis = col[9].replace(".", "").replace(",", ".");

                            double rmnT = Double.parseDouble(remuneracaoTotal);
                            double rmnD = Double.parseDouble(remuneracaoDevida);
                            double LD = Double.parseDouble(liquidosDisponiveis);

                            return new Pessoa(
                                    col[0], col[1], col[2], col[3], col[4],
                                    rmnT, col[6], rmnD, col[8], LD,
                                    OrgaoEnum.fromCodigo(Integer.parseInt(endereco.replaceAll("^(\\d+)_.*$", "$1"))),
                                    MesEnum.fromCodigo(Integer.parseInt(endereco.replaceAll(".*_(\\d{4})(\\d{2})\\.csv", "$2"))),
                                    Integer.valueOf(endereco.replaceAll(".*_(\\d{4})\\d{2}\\.csv", "$1"))
                            );
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            log.error("Erro ao ler linha: " + String.join(";", col), e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .forEach(pessoas::add);
        } catch (IOException e) {
            log.error("Erro ao ler arquivo CSV: " + endereco, e);
        }

        return pessoas;
    }
}
