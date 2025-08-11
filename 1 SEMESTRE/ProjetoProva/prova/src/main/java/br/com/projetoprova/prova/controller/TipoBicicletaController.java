package br.com.projetoprova.prova.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import br.com.projetoprova.prova.entity.TipoBicicleta;
import br.com.projetoprova.prova.service.TipoBicicletaService;

@Controller
@RequestMapping("/tipoBicicletas")
public class TipoBicicletaController {
    @Autowired

    private TipoBicicletaService tipoBicicletaService;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<TipoBicicleta> tipoBicicletas = tipoBicicletaService.findAll();
        model.addAttribute("tipobicicletas", tipoBicicletas);
        return "tipoBicicleta/listaTipoBicicleta";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("tipoBicicleta", new TipoBicicleta());
        return "tipoBicicleta/formularioTipoBicicleta";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute TipoBicicleta tipoBicicleta, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "tipoBicicleta/formularioTipoBicicleta";
            }
            tipoBicicletaService.save(tipoBicicleta);
            return "redirect:/tipoBicicletas/listar";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao salvar tipoBicicleta: " + e.getMessage());
            return "tipoBicicleta/formularioTipoBicicleta";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id, Model model) {
        try {
            tipoBicicletaService.deleteById(id);
            return "redirect:/tipoBicicletas/listar?success=TipoBicicleta excluída com sucesso";
        } catch (Exception e) {
            String mensagem = "Não é possível excluir o tipoBicicleta porque ela está vinculada a um ou mais bicicletas.";
            return "redirect:/tipoBicicletas/listar?error=" + java.net.URLEncoder.encode(mensagem, java.nio.charset.StandardCharsets.UTF_8);
        }
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        try {
            TipoBicicleta tipoBicicleta = tipoBicicletaService.findById(id);
            if (tipoBicicleta == null) {
                return "redirect:/tipoBicicletas/listar?error=TipoBicicleta não encontrada";
            }
            model.addAttribute("tipoBicicleta", tipoBicicleta);
            return "tipoBicicleta/formularioTipoBicicleta";
        } catch (Exception e) {
            return "redirect:/tipoBicicletas/listar?error=" + e.getMessage();
        }
    }
    
}
