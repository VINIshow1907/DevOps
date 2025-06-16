package br.com.projetoprova.prova.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetoprova.prova.entity.TipoBicicleta;
import br.com.projetoprova.prova.repository.TipoBicicletaRepository;

@Service
public class TipoBicicletaService {
    @Autowired

    private  TipoBicicletaRepository tipoBicicletaRepository;

    public List<TipoBicicleta> findAll() {
        return tipoBicicletaRepository.findAll();
    }
    public TipoBicicleta save(TipoBicicleta tipoBicicleta) {
        return tipoBicicletaRepository.save(tipoBicicleta);
    }
    public void deleteById(Integer id) {
        tipoBicicletaRepository.deleteById(id);
    }
    public TipoBicicleta findById(Integer id) {
        return tipoBicicletaRepository.findById(id).orElse(null);
    }
}