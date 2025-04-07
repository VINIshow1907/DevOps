package br.com.fatecads.fatecads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fatecads.fatecads.entity.Curso;
import br.com.fatecads.fatecads.service.CursoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    //Método para listar todos os cursos
    @GetMapping("/listar")
    public String listar(Model model) {
        //Busca todos os cursos
        List<Curso> cursos = cursoService.findAll();
        //Adiciona os cursos 
        model.addAttribute("cursos", cursos);
        //Retorna a página de lista de cursos
        return "/curso/listaCursos";
    }
    
    //Método para abrir o formulário de criação de cursos
    @GetMapping("/criar")
    public String criarFrom( Model model) {
        //adiciona um novo curso ao model
        model.addAttribute("curso", new Curso());
        //retorna a página do formulário de cursos
        return "curso/formularioCurso";
    }

    @PostMapping("salvar")
    public String salvar(@ModelAttribute Curso curso) {
        //Salva o curso
        cursoService.save(curso);
        //Redireciona para a lista de cursos
        return "redirect:/cursos/listar";
    }
    
    //Método para exlcuir um curso
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) 
    {
        //Excluir o curso
        cursoService.deleteById(id);
        return "redirect:/cursos/listar";
    }
    //Método para abrir o formulário de edição de curso
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model)
    {
        //Busca o curso pelo id
        Curso curso = cursoService.findById(id);
        //Adiciona o curso ao modelo
        model.addAttribute("curso", curso);
        //Retorna a página do formulário de curso
        return "curso/formularioCurso";
    }
    
    
}
