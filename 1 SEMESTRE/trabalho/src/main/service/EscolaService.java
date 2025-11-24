package br.com.trabalho.trabalho.service;

import org.springframework.stereotype.Service;

import br.com.trabalho.trabalho.entity.Aluno;
import br.com.trabalho.trabalho.entity.Disciplina;
import br.com.trabalho.trabalho.entity.DisciplinaAluno;
import br.com.trabalho.trabalho.repository.AlunoRepository;
import br.com.trabalho.trabalho.repository.DisciplinaAlunoRepository;
import br.com.trabalho.trabalho.repository.DisciplinaRepository;
import jakarta.transaction.Transactional;

@Service
public class EscolaService {

    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaAlunoRepository disciplinaAlunoRepository;

    public EscolaService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository,
            DisciplinaAlunoRepository disciplinaAlunoRepository) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.disciplinaAlunoRepository = disciplinaAlunoRepository;
    }

    public Aluno findAlunoByUsername(String username) {
        return alunoRepository.findByUsername(username);
    }

    public Iterable<Disciplina> findAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @Transactional
    public void matricularAlunoEmDisciplina(String username, Long disciplinaId) {
        Aluno aluno = alunoRepository.findByUsername(username);
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow();

        DisciplinaAluno disciplinaAluno = new DisciplinaAluno();
        disciplinaAluno.setAluno(aluno);
        disciplinaAluno.setDisciplina(disciplina);
        disciplinaAluno.setNomeprofessor("Professor Exemplo"); // Defina o nome do professor conforme necessário

        disciplinaAlunoRepository.save(disciplinaAluno);
    }
}