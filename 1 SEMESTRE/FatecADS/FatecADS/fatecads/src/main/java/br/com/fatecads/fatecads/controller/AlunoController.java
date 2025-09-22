package br.com.fatecads.fatecads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fatecads.fatecads.dto.AlunoTelefone;
import br.com.fatecads.fatecads.entity.Aluno;
import br.com.fatecads.fatecads.entity.Curso;
import br.com.fatecads.fatecads.service.AlunoService;
import br.com.fatecads.fatecads.service.CursoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;

    @Autowired 
    private CursoService cursoService;

    //Método para listar todos os alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        // view: src/main/resources/templates/aluno/listaAlunos.html
        return "aluno/listaAlunos";
    }
    
    //Método para abrir o formulário de criação de alunos
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        // view: src/main/resources/templates/aluno/formularioAluno.html
        return "aluno/formularioAluno";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno) {
        alunoService.save(aluno);
        return "redirect:/alunos/listar";
    }
    
    //Método para excluir um aluno
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        alunoService.deleteById(id);
        return "redirect:/alunos/listar";
    }

    //Método para abrir o formulário de edição de aluno
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoService.findById(id);
        model.addAttribute("aluno", aluno);
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        // view: src/main/resources/templates/aluno/formularioAluno.html
        return "aluno/formularioAluno";
    }
    
    @GetMapping("/listar-nome-telefone")
    public String listarNomeTelefone(Model model) {
        List<AlunoTelefone> alunos = alunoService.buscarNomesETelefone();
        model.addAttribute("alunos", alunos);
        // view: src/main/resources/templates/aluno/listaNomeTelefone.html
        return "aluno/listaNomeTelefone";
    }
}
