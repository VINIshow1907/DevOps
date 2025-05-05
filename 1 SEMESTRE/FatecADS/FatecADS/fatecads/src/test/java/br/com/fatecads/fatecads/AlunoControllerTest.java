package br.com.fatecads.fatecads;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fatecads.fatecads.controller.AlunoController;
import br.com.fatecads.fatecads.entity.Aluno;
import br.com.fatecads.fatecads.service.AlunoService;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Test
    void testSalvarAluno() throws Exception{
        Aluno aluno = new Aluno();
        aluno.setNomeAluno("João");
        aluno.setEndAluno("Rua A");
        aluno.setTelAluno("17999999999");
        aluno.setRaAluno(123456);

        mockMvc.perform(post("alunos/salvar").flashAttr("aluno", aluno)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("alunos/listar"));
        
    }

    
}
