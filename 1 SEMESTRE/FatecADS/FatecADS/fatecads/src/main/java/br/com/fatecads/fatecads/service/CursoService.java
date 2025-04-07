package br.com.fatecads.fatecads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatecads.fatecads.entity.Curso;
import br.com.fatecads.fatecads.repository.CursoRepository;

@Service
public class CursoService {
     //Injeção de dependência do repositório de cursos
    @Autowired
    private CursoRepository cursoRepository;

    //Método para buscar todos os cursos
    public List<Curso> findAll(){
        //Retorna todos os cursos 
        return cursoRepository.findAll();
    }
    //Método para salvar um curso
    public Curso save(Curso curso)
    {
        return cursoRepository .save(curso);
    }
    // Método para excluir um curso pelo id
    public void deleteById(Integer id)
    {
        cursoRepository.deleteById(id);
    }
    //Método para buscar um curso pelo id
    public Curso findById(Integer id)
    {
    //retorna o curso com o id especificado ou null se não existir
        return cursoRepository.findById(id).orElse(null);
}
}
