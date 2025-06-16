package br.com.projetoprova.prova.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String modelo;

    @Column(nullable = false, length = 40)
    private String ano;

    @Column(nullable = false, length = 40)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "idTipoBicicleta_fk")
    private TipoBicicleta tipoBicicleta;

}
