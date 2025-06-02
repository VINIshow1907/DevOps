package br.com.fatecads.fatecads.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Aluno {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAluno;

    @Column(nullable = false, length = 40)
    private String nomeAluno; 

    @Column(nullable = false, length = 40)
    private String endAluno;

    @Column(nullable = false, length = 11)
    private String telAluno;

    private Integer raAluno; 
    
    @ManyToOne
    @JoinColumn(name = "idCurso_fk")
    private Curso curso;
}