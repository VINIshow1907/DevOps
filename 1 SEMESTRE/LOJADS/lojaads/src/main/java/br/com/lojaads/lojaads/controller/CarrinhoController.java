package br.com.lojaads.lojaads.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.lojaads.lojaads.entity.Pedido;
import br.com.lojaads.lojaads.entity.Usuario;
import br.com.lojaads.lojaads.repository.UsuarioRepository;
import br.com.lojaads.lojaads.service.CarrinhoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
    
    private final CarrinhoService carrinhoService;
    private final UsuarioRepository usuarioRepository;
 
    public CarrinhoController(CarrinhoService carrinhoService, UsuarioRepository usuarioRepository) {
        this.carrinhoService = carrinhoService;
        this.usuarioRepository = usuarioRepository;
    }
 
    @GetMapping
    public String visualizarCarrinho(Model model, Authentication auth) {
        Usuario usuario = obterUsuarioLogado(auth);
        Pedido pedido = carrinhoService.obterCarrinho(usuario);
        if (pedido == null || pedido.getItens() == null || pedido.getItens().isEmpty()) {
            model.addAttribute("pedidoVazio", true);
            // Poderia enviar um Pedido vazio para evitar problemas na view
            model.addAttribute("pedido", null);
        } else {
            model.addAttribute("pedido", pedido);
            model.addAttribute("pedidoVazio", false);
        }
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && aut.isAuthenticated()) {
            String username = aut.getName();
            model.addAttribute("nomeUsuario", username);
        }
        return "carrinho";
    }
 
@PostMapping("/adicionar")
public String adicionarAoCarrinho(@RequestParam Long idProduto, @RequestParam int quantidade, Authentication auth) {
    Usuario usuario = obterUsuarioLogado(auth);
    for (int i = 0; i < quantidade; i++) {
        carrinhoService.adicionarProduto(usuario, idProduto);
    }
    return "redirect:/home";
}
 
 
    @GetMapping("/remover/{idItem}")
    public String removerItem(@PathVariable Long idItem, Authentication auth) {
        Usuario usuario = obterUsuarioLogado(auth);
        carrinhoService.removerItem(usuario, idItem);
        return "redirect:/carrinho";
    }
 
    private Usuario obterUsuarioLogado(Authentication auth) {
        String login = auth.getName();
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
