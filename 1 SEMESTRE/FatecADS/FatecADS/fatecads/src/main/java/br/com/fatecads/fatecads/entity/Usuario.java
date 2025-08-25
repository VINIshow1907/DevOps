package br.com.fatecads.fatecads.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idUsuario;
    
    @Column(nullable = false,length = 40)
    private String nomeUsuario;

    @Column(nullable = false,length = 11)
    private String cpfUsuario;

    @Column(nullable = false,length = 40)
    private String loginUsuario;

    @Column(nullable = false,length = 500)
    private String senhaUsuario;
    
    private String role = "ROLE_USER";
}