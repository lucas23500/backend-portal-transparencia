package tads.ts.ifam.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tads.ts.ifam.LeitorCsv.LeitorCsv;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.PessoaRepository;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CargaInicial {

    private final PessoaRepository pessoaRepository;


    private final LeitorCsv leitorCsv;

    @Autowired
    public CargaInicial(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.leitorCsv = new LeitorCsv();
    }

    public void salvaRepo() throws IOException {
        List<String> lista = leitorCsv.listarNomesArquivosCSV();

        lista.parallelStream().forEach(arquivoCSV -> {
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
