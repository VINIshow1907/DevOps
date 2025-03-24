package br.com.fatecads.fatecads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecads.fatecads.entity.Aluno;
import br.com.fatecads.fatecads.repository.alunoRepository;

@Service
public class AlunoService {
    
    //Injeção de dependência do repositório de alunos
    @Autowired
    private alunoRepository alunoRepository;

    //Método para buscar todos os alunos
    public List<Aluno> findAll(){
        //Retorna todos os alunos
        return alunoRepository.findAll();
    }
}
