package br.com.projetoprova.prova.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.projetoprova.prova.entity.Bicicleta;
import br.com.projetoprova.prova.service.BicicletaService;
import br.com.projetoprova.prova.service.TipoBicicletaService;

@Controller
@RequestMapping("/bicicleta")
public class BicicletaController {

    @Autowired
    private BicicletaService bicicletaService;

    @Autowired
    private TipoBicicletaService tipoBicicletaService;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Bicicleta> bicicletas = bicicletaService.findAll();
        model.addAttribute("bicicletas", bicicletas);
        return "bicicleta/listaBicicleta";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());
        model.addAttribute("tiposBicicleta", tipoBicicletaService.findAll());
        return "bicicleta/formularioBicicleta";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Bicicleta bicicleta) {
        bicicletaService.save(bicicleta);
        return "redirect:/bicicleta/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        bicicletaService.deleteById(id);
        return "redirect:/bicicleta/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Bicicleta bicicleta = bicicletaService.findById(id);
        model.addAttribute("bicicleta", bicicleta);
        model.addAttribute("tiposBicicleta", tipoBicicletaService.findAll());
        return "bicicleta/formularioBicicleta";
    }
}
