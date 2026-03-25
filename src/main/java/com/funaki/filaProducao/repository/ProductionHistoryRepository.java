package com.funaki.filaProducao.repository;

import com.funaki.filaProducao.model.ProductionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductionHistoryRepository extends JpaRepository<ProductionHistory, UUID> {

}
