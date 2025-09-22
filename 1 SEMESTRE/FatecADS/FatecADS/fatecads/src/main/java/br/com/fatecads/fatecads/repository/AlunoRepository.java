package br.com.fatecads.fatecads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fatecads.fatecads.dto.AlunoTelefone;
import br.com.fatecads.fatecads.entity.Aluno;

//JpaRepository = local onde os métodos base estão implementados 
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    // Listar o nome e telefone de todos os alunos
    @Query("SELECT new br.com.fatecads.fatecads.dto.AlunoTelefone(a.nomeAluno, a.telAluno) FROM Aluno a")
    List<AlunoTelefone> buscarNomesETelefone();
    
}
