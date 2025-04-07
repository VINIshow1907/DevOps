package br.com.fatecads.fatecads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fatecads.fatecads.entity.Aluno;
import br.com.fatecads.fatecads.service.AlunoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;

    //Método para listar todos os alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        //Busca todos os alunos
        List<Aluno> alunos = alunoService.findAll();
        //Adiciona os alunos 
        model.addAttribute("alunos", alunos);
        //Retorna a página de lista de alunos
        return "/aluno/listaAlunos";
    }
    
    //Método para abrir o formulário de criação de alunos
    @GetMapping("/criar")
    public String criarFrom( Model model) {
        //adiciona um novo aluno ao model
        model.addAttribute("aluno", new Aluno());
        //retorna a página do formulário de alunos
        return "/aluno/formularioAluno";
    }

    @PostMapping("salvar")
    public String salvar(@ModelAttribute Aluno aluno) {
        //Salva o aluno
        alunoService.save(aluno);
        //Redireciona para a lista de alunos
        return "redirect:/alunos/listar";
    }
    
    //Método para exlcuir um aluno
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) 
    {
        //Excluir o aluno
        alunoService.deleteById(id);
        return "redirect:/alunos/listar";
    }
    //Método para abrir o formulário de edição de aluno
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model)
    {
        //Busca o aluno pelo id
        Aluno aluno = alunoService.findById(id);
        //Adiciona o aluno ao modelo
        model.addAttribute("aluno", aluno);
        //Retorna a página do formulário de aluno
        return "aluno/formularioAluno";
    }
    
    
}
