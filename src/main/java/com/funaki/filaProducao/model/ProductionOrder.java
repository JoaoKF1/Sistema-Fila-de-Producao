package com.funaki.filaProducao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_BOLETIM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column (nullable = false, unique = true)
    private String numProductionOrder;

    @ManyToOne
    @JoinColumn (name = "id_User")
    private User user;

    @Column(name = "metros_totais")
    private int metrosTotais;

    @Column(name = "metros_restantes")
    private int metrosRestante;

    private String gramatura;

    private String formato;

    private String onda;

    @Column(name = "data_Entrada", nullable = false)
    private LocalDateTime dataEntrada;

    private int ajustes;


}
