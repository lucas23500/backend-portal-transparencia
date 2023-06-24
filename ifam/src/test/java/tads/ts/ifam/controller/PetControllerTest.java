package tads.ts.ifam.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveListasRacasCadastradas() throws Exception {

        URI uri = new URI("/api/pets");

        RequestBuilder request = MockMvcRequestBuilders.get(uri);

        ResultMatcher statusEsperado = MockMvcResultMatchers.status().is(200);


        String jsonRetorno= "[{\"id\":1,\"nome\":\"LAYLA\",\"chip\":\"12345678901234\",\"idade\":13,\"raca\":{\"id\":1,\"nome\":\"YORKSHIRE\",\"descricao\":\"DOG PEQUENO\",\"especie\":{\"id\":1,\"nome\":\"CANINA\",\"descricao\":\"CACHORRO EM GERAL\"}}}]";

        ResultMatcher conteudoEsperado = MockMvcResultMatchers.content().json(jsonRetorno);


        mvc.perform(request).andExpect(statusEsperado).andExpect(conteudoEsperado);

    }
}
