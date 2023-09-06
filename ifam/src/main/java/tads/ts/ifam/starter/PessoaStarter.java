package tads.ts.ifam.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tads.ts.ifam.LeitorCsv.LeitorCsv;
import tads.ts.ifam.controller.PessoaController;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.PessoaRepository;

import java.io.IOException;
import java.util.List;

@Component
public class PessoaStarter {

    private final PessoaRepository pessoaRepository;


    @Autowired
    public PessoaStarter(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }



    public void salvaRepo() throws IOException {

        LeitorCsv leitorCsv = new LeitorCsv();

        List<String> lista = new LeitorCsv().listarNomesArquivosCSV();

        for(String pessoa : lista){
            List<Pessoa> pessoaList = leitorCsv.lerCsv(pessoa);
            pessoaRepository.saveAll(pessoaList);
        }
//        List<Pessoa> pessoaList = leitorCsv.lerCsv("C:/dev/projetos/tcc-projeto/atividadeGPS/ifam/src/main/resources/dados.csv");
//
//        PessoaController pessoaController = new PessoaController();
//
//        pessoaRepository.saveAll(pessoaList);

    }


}
