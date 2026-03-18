package com.funaki.filaProducao.repository;

import com.funaki.filaProducao.model.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, UUID> {

    Optional<ProductionOrder> findByNumBoletim(String numBoletim);

}
