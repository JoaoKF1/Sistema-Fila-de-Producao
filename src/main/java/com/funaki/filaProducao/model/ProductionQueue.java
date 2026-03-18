package com.funaki.filaProducao.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "production_Queue")
public class ProductionQueue {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private ProductionOrder productionOrder;

    private Integer position;

    private String status;
}
