package br.com.fatecads.fatecads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatecads.fatecads.entity.Curso;

//JpaRepository = local onde os métodos base estão implementados 
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
 }
