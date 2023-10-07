package tads.ts.ifam.LeitorCsv;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataAccessException;
import tads.ts.ifam.controller.*;
import tads.ts.ifam.model.*;
import tads.ts.ifam.repository.*;

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
    private final CargoController cargoController;
    private final FuncaoController funcaoController;
    private final LotacaoController lotacaoController;
    private final OrgaoController orgaoController;
    private final VinculoController vinculoController;

    private final PessoaRepository pessoaRepository;

    private final CargoRepository cargoRepository;
    private final FuncaoRepository funcaoRepository;
    private final LotacaoRepository lotacaoRepository;
    private final OrgaoRepository orgaoRepository;
    private final VinculoRepository vinculoRepository;



    public LeitorCsv(PessoaRepository pessoaRepository,
                     CargoController cargoController, CargoRepository cargoRepository,
                     FuncaoController funcaoController, FuncaoRepository funcaoRepository,
                     LotacaoController lotacaoController, LotacaoRepository lotacaoRepository,
                     OrgaoController orgaoController, OrgaoRepository orgaoRepository,
                     VinculoController vinculoController, VinculoRepository vinculoRepository) {
        //controller
        this.cargoController = cargoController;
        this.funcaoController = funcaoController;
        this. lotacaoController = lotacaoController;
        this.orgaoController = orgaoController;
        this.vinculoController = vinculoController;

        //repository
        this.cargoRepository = cargoRepository;
        this.orgaoRepository = orgaoRepository;
        this.funcaoRepository = funcaoRepository;
        this.lotacaoRepository = lotacaoRepository;
        this. vinculoRepository = vinculoRepository;

        this.pessoaRepository = pessoaRepository;
    }

//    public LeitorCsv(PessoaRepository pessoaRepository, LotacaoRepository lotacaoRepository) {
//        this.pessoaRepository = pessoaRepository;
//    }

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

                            salvaCargo(col[2]);
                            salvaLotacao(col[1]);
                            salvaFuncao(col[3]);
                            salvaOrgao(OrgaoEnum.fromCodigo(Integer.parseInt(endereco.replaceAll("^(\\d+)_.*$", "$1"))));
                            salvaVinculo(col[4]);

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

    private void salvaLotacao(String col) {

        Lotacao lotacao = new Lotacao(col);
        if (lotacaoController.searchByName(col) == null ||
                !lotacaoController.searchByName(col).getNome().equals(col)) {
            lotacaoRepository.save(lotacao);
        }
    }

    private void salvaFuncao(String funcao) {
        try {
            Funcao lotacao = new Funcao(funcao);
            if (funcaoController.searchByName(funcao) == null ||
                    !funcaoController.searchByName(funcao).getNome().replaceAll(" ", "")
                            .equals(funcao.replaceAll(" ", ""))) {
                funcaoRepository.save(lotacao);
            }
        }catch (DataAccessException e){
            System.out.println("ERRO: jorge " + e);

        }
    }

    private void salvaCargo(String cargo) {

        Cargo lotacao = new Cargo(cargo);
        if (cargoController.searchByName(cargo) == null ||
                !cargoController.searchByName(cargo).getNome().equals(cargo)) {
            cargoRepository.save(lotacao);
            cargoRepository.save(lotacao);
        }
    }
    private void salvaOrgao(String funcao) {

        Orgao lotacao = new Orgao(funcao);
        if (orgaoController.searchByName(funcao) == null ||
                !orgaoController.searchByName(funcao).getNome().equals(funcao)) {
            orgaoRepository.save(lotacao);
        }
    }
    private void salvaVinculo(String funcao) {

        Vinculo lotacao = new Vinculo(funcao);
        if (vinculoController.searchByName(funcao) == null ||
                !vinculoController.searchByName(funcao).getNome().equals(funcao)) {
            vinculoRepository.save(lotacao);
        }
    }
}