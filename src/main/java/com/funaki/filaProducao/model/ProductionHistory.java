package com.funaki.filaProducao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "production_queue_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "production_order_id", nullable = false)
    private ProductionOrder productionOrder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer oldPosition;

    private Integer newPosition;

    @Enumerated(EnumType.STRING)
    private QueueAction action;

    private LocalDateTime timestamp;


}
