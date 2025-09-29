package br.com.fatecads.fatecads.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Curso {
 
  

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCurso;

    @Column(nullable = false, length = 40)
    private String nomeCurso; 

    @Column(nullable = false, length = 40)
    private Integer cargaHorariaCurso;

    @Column(nullable = false, length = 11)
    private String periodoCurso;
     
    @ManyToMany
    @JoinTable(name = "curso_disciplina",joinColumns = @JoinColumn(name = "curso_id"),inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    ) 

    private List<Disciplina> disciplinas;
}

