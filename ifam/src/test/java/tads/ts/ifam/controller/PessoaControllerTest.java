package tads.ts.ifam.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tads.ts.ifam.repository.CidadeRepository;
import tads.ts.ifam.repository.EstadoRepository;
import tads.ts.ifam.repository.PessoaRepository;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private CidadeRepository cidadeRepository ;

    @Autowired
    private EstadoRepository estadoRepository ;

    @Autowired
    private PessoaRepository pessoaRepository ;

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveListarPessoasCadastradas() throws Exception {

        URI uri = new URI("/api/pessoas");

        RequestBuilder request = MockMvcRequestBuilders.get(uri);

        ResultMatcher statusEsperado = MockMvcResultMatchers.status().is(200);

        String jsonRetorno= "[{\"id\":1,\"nome\":\"ALUNO1\",\"telefone\":\"9299999999\",\"email\":\"ALUNO1.EMAIL@IFAM.EDU.BR\",\"cidade\":{\"id\":1,\"nome\":\"MANAUS\",\"ibge\":\"1300\",\"estado\":{\"id\":1,\"nome\":\"AMAZONAS\",\"sigla\":\"AM\",\"ibge\":\"13\"}}},{\"id\":2,\"nome\":\"ALUNO2\",\"telefone\":\"9299999999\",\"email\":\"ALUNO2.EMAIL@IFAM.EDU.BR\",\"cidade\":{\"id\":1,\"nome\":\"MANAUS\",\"ibge\":\"1300\",\"estado\":{\"id\":1,\"nome\":\"AMAZONAS\",\"sigla\":\"AM\",\"ibge\":\"13\"}}},{\"id\":3,\"nome\":\"ALUNO3\",\"telefone\":\"9999999999\",\"email\":\"ALUNO3.EMAIL@IFAM.EDU.BR\",\"cidade\":{\"id\":1,\"nome\":\"MANAUS\",\"ibge\":\"1300\",\"estado\":{\"id\":1,\"nome\":\"AMAZONAS\",\"sigla\":\"AM\",\"ibge\":\"13\"}}}]";

        ResultMatcher conteudoEsperado = MockMvcResultMatchers.content().json(jsonRetorno);

        mvc.perform(request).andExpect(statusEsperado).andExpect(conteudoEsperado);

    }


    @Test
    public void deveConsultarPessoaPorEmail() throws Exception {

        URI uri = new URI("/api/pessoas/email/ALUNO1.EMAIL@IFAM.EDU.BR");

        RequestBuilder request = MockMvcRequestBuilders.get(uri);

        ResultMatcher statusEsperado = MockMvcResultMatchers.status().is(200);

        String jsonRetorno= "{\"id\":1,\"nome\":\"ALUNO1\",\"telefone\":\"9299999999\",\"email\":\"ALUNO1.EMAIL@IFAM.EDU.BR\",\"cidade\":{\"id\":1,\"nome\":\"MANAUS\",\"ibge\":\"1300\",\"estado\":{\"id\":1,\"nome\":\"AMAZONAS\",\"sigla\":\"AM\",\"ibge\":\"13\"}}}";

        ResultMatcher conteudoEsperado = MockMvcResultMatchers.content().json(jsonRetorno);


        mvc.perform(request).andExpect(statusEsperado).andExpect(conteudoEsperado);


    }

    @Test
    public void deveConsultarPessoaPorId() throws Exception {

        URI uri = new URI("/api/pessoas/2");

        RequestBuilder request = MockMvcRequestBuilders.get(uri);

        ResultMatcher statusEsperado = MockMvcResultMatchers.status().is(200);

        String jsonRetorno= "{\"id\":2,\"nome\":\"ALUNO2\",\"telefone\":\"9299999999\",\"email\":\"ALUNO2.EMAIL@IFAM.EDU.BR\",\"cidade\":{\"id\":1,\"nome\":\"MANAUS\",\"ibge\":\"1300\",\"estado\":{\"id\":1,\"nome\":\"AMAZONAS\",\"sigla\":\"AM\",\"ibge\":\"13\"}}}";

        ResultMatcher conteudoEsperado = MockMvcResultMatchers.content().json(jsonRetorno);

        mvc.perform(request).andExpect(statusEsperado).andExpect(conteudoEsperado);
    }

    @Test
    public void deveInserirNovaPessoa() throws Exception {

        URI uri = new URI("/api/pessoas/");

        String jsonRetorno= "{\"nome\":\"ALUNO11\",\"telefone\":\"9299999999\",\"email\":\"ALUNO11.EMAIL@IFAM.EDU.BR\",\"cidade\":{\"id\":1,\"nome\":\"MANAUS\",\"ibge\":\"1300\",\"estado\":{\"id\":1,\"nome\":\"AMAZONAS\",\"sigla\":\"AM\",\"ibge\":\"13\"}}}";

        RequestBuilder request = MockMvcRequestBuilders.post(uri).
                content(jsonRetorno).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusEsperado = MockMvcResultMatchers.status().is(200);


        mvc.perform(request).andExpect(statusEsperado);


        URI uri2 = new URI("/api/pessoas/4");

        RequestBuilder request2 = MockMvcRequestBuilders.get(uri2);

        ResultMatcher statusEsperado2 = MockMvcResultMatchers.status().is(200);

        ResultMatcher conteudoEsperado2 = MockMvcResultMatchers.content().json(jsonRetorno);

        mvc.perform(request2).andExpect(statusEsperado2).andExpect(conteudoEsperado2);

    }
}
