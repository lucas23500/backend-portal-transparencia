package tads.ts.ifam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tads.ts.ifam.repository.PessoaRepository;
import tads.ts.ifam.starter.CargaInicial;

import java.io.IOException;

@SpringBootApplication
public class IfamApplication {

	@Autowired
	PessoaRepository pessoaRepository;

	public static void main(String[] args) throws IOException {

		ApplicationContext context = SpringApplication.run(IfamApplication.class, args);

		CargaInicial cargaInicial = context.getBean(CargaInicial.class);

		cargaInicial.salvaRepo();

	}

}
