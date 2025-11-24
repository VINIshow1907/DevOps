package br.com.trabalho.trabalho.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.trabalho.trabalho.entity.Aluno;
import br.com.trabalho.trabalho.entity.Disciplina;
import br.com.trabalho.trabalho.service.EscolaService;

@Controller
@RequestMapping("/escola")
public class EscolaController {

    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Aluno aluno = escolaService.findAlunoByUsername(username);
        model.addAttribute("aluno", aluno);
        model.addAttribute("disciplinas", escolaService.findAllDisciplinas());
        return "dashboard";
    }

    @PostMapping("/matricular/{disciplinaId}")
    public String matricular(@PathVariable Long disciplinaId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        escolaService.matricularAlunoEmDisciplina(username, disciplinaId);
        return "redirect:/escola/dashboard";
    }
}