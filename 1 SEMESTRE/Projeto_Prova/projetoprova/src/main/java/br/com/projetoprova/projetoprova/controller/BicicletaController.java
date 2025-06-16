package br.com.projetoprova.projetoprova.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.com.bicicleta.bicicleta.entity.Bicicleta;
import br.com.bicicleta.bicicleta.entity.TipoBicicleta;
import br.com.bicicleta.bicicleta.service.BicicletaService;
import br.com.bicicleta.bicicleta.service.TipoBicicletaService;

@Controller
@RequestMapping("/bicicleta")
public class BicicletaController {
    @Autowired
    private BicicletaService bicicletaService;
    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Bicicleta> bibicletas = bicicletaService.findAll();
        model.addAttribute("bicicletas", bicicletas);
        return "dentista/listabicicletas";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());
        List<Especialidade> especialidades = especialidadeService.findAll();
        model.addAttribute("especialidades", especialidades);
        return "bicicleta/formularioBicicleta";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Bicicleta bicicleta) {
        bicicletaService.save(bicicleta);
        return "redirect:/bicicletas/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        bicicletaService.deleteById(id);
        return "redirect:/bicicletas/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Bicicleta bicicleta = bicicletaService.findById(id);
        List<Especialidade> especialidades = especialidadeService.findAll();
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("bicicleta", bicicleta);
        return "bicicleta/formularioBicicleta";
    }
} 