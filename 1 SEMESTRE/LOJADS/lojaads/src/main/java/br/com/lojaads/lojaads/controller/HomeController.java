package br.com.lojaads.lojaads.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.lojaads.lojaads.repository.ProdutoRepository;

public class HomeController {
    
    private final ProdutoRepository produtoRepository;
 
    public HomeController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
 
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            model.addAttribute("nomeUsuario", username);
        }
        return "home";
    }
}
