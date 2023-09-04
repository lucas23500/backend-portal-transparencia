package tads.ts.ifam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tads.ts.ifam.LeitorCsv.LeitorCsv;
import tads.ts.ifam.controller.PessoaController;
import tads.ts.ifam.model.Pessoa;
import tads.ts.ifam.repository.PessoaRepository;
import tads.ts.ifam.starter.PessoaStarter;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class IfamApplication {

	@Autowired
	PessoaRepository pessoaRepository;

	public static void main(String[] args) throws IOException {

		ApplicationContext context = SpringApplication.run(IfamApplication.class, args);

		PessoaStarter pessoaStarter = context.getBean(PessoaStarter.class);

		pessoaStarter.salvaRepo();

	}

}
