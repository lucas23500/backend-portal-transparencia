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


    @Autowired
    public CargaInicial(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }



    public void salvaRepo() throws IOException {

        LeitorCsv leitorCsv = new LeitorCsv();

        List<String> lista = new LeitorCsv().listarNomesArquivosCSV();


        for(String arquivoCSV : lista){
            List<Pessoa> pessoaList = leitorCsv.lerCsv(arquivoCSV);
            pessoaRepository.saveAll(pessoaList);
            log.info("Arquivo CSV [ " + arquivoCSV + " ] Salvo com Sucesso na Base");
        }
        log.info("-=-=-= Carga inicial Finalizada -=-=-=");
    }
}
