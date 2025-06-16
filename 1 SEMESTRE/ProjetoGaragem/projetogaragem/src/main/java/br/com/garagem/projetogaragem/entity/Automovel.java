package br.com.garagem.projetogaragem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String modelo;

    @Column(nullable = false, length = 40)
    private Integer ano;

    @Column(nullable = false, length = 40)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

}