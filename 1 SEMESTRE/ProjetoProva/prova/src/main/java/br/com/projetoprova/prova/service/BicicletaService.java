package br.com.projetoprova.prova.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetoprova.prova.entity.Bicicleta;
import br.com.projetoprova.prova.repository.BicicletaRepository;

@Service
public class BicicletaService {
     @Autowired
    private BicicletaRepository bicicletaRepository;

    public List<Bicicleta> findAll() {
        return bicicletaRepository.findAll();
    }

    public Bicicleta save(Bicicleta bicicleta) {
        return bicicletaRepository.save(bicicleta);
    }

    public void deleteById(Integer id) {
        bicicletaRepository.deleteById(id);
    }

    public Bicicleta findById(Integer id) {
        return bicicletaRepository.findById(id).orElse(null);
    }  
}
