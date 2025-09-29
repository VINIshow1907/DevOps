package br.com.fatecads.fatecads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fatecads.fatecads.entity.Disciplina;
import br.com.fatecads.fatecads.repository.DisciplinaRepository;

public class DisciplinaSerice {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina save(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }
    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }
    public void deleteById(Long id) {
        disciplinaRepository.deleteById(id);
    }
    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }
    public List<Disciplina> findAllById(List<Long> ids) {
        return disciplinaRepository.findAllById(ids);
    }
    
}
