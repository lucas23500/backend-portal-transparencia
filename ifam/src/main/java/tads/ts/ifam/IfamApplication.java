package tads.ts.ifam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tads.ts.ifam.repository.PessoaRepository;
import tads.ts.ifam.starter.CargaInicial;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class IfamApplication {

	@Autowired
	PessoaRepository pessoaRepository;

	public static void main(String[] args) throws IOException {

		ApplicationContext context = SpringApplication.run(IfamApplication.class, args);

		long tempoIninicial = System.currentTimeMillis();

		CargaInicial cargaInicial = context.getBean(CargaInicial.class);

		cargaInicial.salvaRepo();

		long tempoFinal = System.currentTimeMillis();

		long tempoDecorrido = tempoFinal - tempoIninicial;
		long segundosDecorridos = Math.round(tempoDecorrido / 1000.0);

		log.info("Carga inicial finalizada em " + segundosDecorridos + " segundos");
	}

}
