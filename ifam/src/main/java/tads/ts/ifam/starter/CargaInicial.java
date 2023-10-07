package tads.ts.ifam.starter;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tads.ts.ifam.LeitorCsv.LeitorCsv;
import tads.ts.ifam.controller.*;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CargaInicial {


    private final LeitorCsv leitorCsv;


    //controller
    private CargoController cargoController;
    private FuncaoController funcaoController;
    private final LotacaoController lotacaoController;
    private OrgaoController orgaoController;
    private VinculoController vinculoController;

    //repository
    private final PessoaRepository pessoaRepository;
    private CargoRepository cargoRepository;
    private FuncaoRepository funcaoRepository;
    private final LotacaoRepository lotacaoRepository;
    private OrgaoRepository orgaoRepository;
    private VinculoRepository vinculoRepository;

    @Autowired
    public CargaInicial(
                        CargoController cargoController, FuncaoController funcaoController, LotacaoController lotacaoController, OrgaoController orgaoController, VinculoController vinculoController,
                        PessoaRepository pessoaRepository,
                        CargoRepository cargoRepository, FuncaoRepository funcaoRepository, LotacaoRepository lotacaoRepository, OrgaoRepository orgaoRepository, VinculoRepository vinculoRepository) {

        this.cargoRepository = cargoRepository;
        this.funcaoRepository = funcaoRepository;
        this.lotacaoRepository = lotacaoRepository;
        this.pessoaRepository = pessoaRepository;
        this.orgaoRepository = orgaoRepository;
        this.vinculoRepository = vinculoRepository;

        this.cargoController = cargoController;
        this.funcaoController = funcaoController;
        this.lotacaoController = lotacaoController;
        this.vinculoController = vinculoController;
        this.orgaoController = orgaoController;

        this.leitorCsv = new LeitorCsv(pessoaRepository,
                cargoController, cargoRepository,
                funcaoController, funcaoRepository,
                lotacaoController, lotacaoRepository,
                orgaoController, orgaoRepository,
                vinculoController, vinculoRepository);
    }


//    @Autowired
//    public CargaInicial(PessoaRepository pessoaRepository, LotacaoRepository lotacaoRepository, LotacaoController lotacaoController) {
//        this.pessoaRepository = pessoaRepository;
//        this.lotacaoRepository = lotacaoRepository;
//        this.lotacaoController = lotacaoController;
//        this.leitorCsv = new LeitorCsv(pessoaRepository,
//                lotacaoRepository, lotacaoController);
//
//    }

    public void salvaRepo() throws IOException {
        List<String> lista = leitorCsv.listarNomesArquivosCSV();

        lista.forEach(arquivoCSV -> {
            try {
                List<Pessoa> pessoaList = leitorCsv.lerCsv(arquivoCSV);
                pessoaRepository.saveAll(pessoaList);
                log.info("Arquivo CSV [ " + arquivoCSV + " ] Salvo com Sucesso na Base");
            } catch (IOException e) {
                log.error("Erro ao processar o arquivo CSV [ " + arquivoCSV + " ]: " + e.getMessage(), e);
            }
        });

        log.info("-=-=-= Carga inicial Finalizada -=-=-=");
    }
}
