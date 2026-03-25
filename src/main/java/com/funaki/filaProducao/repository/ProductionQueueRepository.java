package com.funaki.filaProducao.repository;

import com.funaki.filaProducao.model.ProductionOrder;
import com.funaki.filaProducao.model.ProductionQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductionQueueRepository extends JpaRepository<ProductionQueue, UUID> {

    List<ProductionQueue> findAllByOrderByPositionAsc();

    @Query("SELECT MAX q.position FROM ProductionQueue q")
    Integer findLastPosition();

    boolean existsByProductionOrder(ProductionOrder productionOrder);

    List<ProductionQueue> findByPositionGreaterThanOrderByPositionAsc(Integer position);
}
