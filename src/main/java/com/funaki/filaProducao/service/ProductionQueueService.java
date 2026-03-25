package com.funaki.filaProducao.service;

import com.funaki.filaProducao.model.ProductionOrder;
import com.funaki.filaProducao.model.ProductionQueue;
import com.funaki.filaProducao.model.QueueAction;
import com.funaki.filaProducao.repository.ProductionOrderRepository;
import com.funaki.filaProducao.repository.ProductionQueueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductionQueueService {

    @Autowired
    private ProductionQueueRepository productionQueueRepository;

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    public List<ProductionQueue> getQueue() {

        return productionQueueRepository.findAllByOrderByPositionAsc();
    }

    @Transactional
    public void removeFromQueue(UUID queueId) {

        ProductionQueue queueItem = productionQueueRepository.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Posição da fila não encontrada"));

        Integer removedPosition = queueItem.getPosition();

        if (queueItem.getStatus() != QueueAction.WAITING) {
            throw new RuntimeException("É possível remover apenas boletins que estão em espera na fila");
        }

        productionQueueRepository.delete(queueItem);

        List<ProductionQueue> itemsAfter = productionQueueRepository
                .findByPositionGreaterThanOrderByPositionAsc(removedPosition);

        for (ProductionQueue item : itemsAfter) {
            item.setPosition(item.getPosition() - 1);
            productionQueueRepository.save(item);
        }
    }

    @Transactional
    public void reorderQueue(List<UUID> queueIds) {
        int position = 1;

        for (UUID id : queueIds) {

            ProductionQueue item = productionQueueRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            item.setPosition(position++);
            productionQueueRepository.save(item);
        }
    }

    @Transactional
    public void loadProductionOrder(String numProductionOrder) {
        ProductionOrder order = productionOrderRepository.findByNumProductionOrder(numProductionOrder)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));

        boolean exists = productionQueueRepository.existsByProductionOrder(order);

            if(exists) {
                throw new RuntimeException("Ordem já está na fila!");
            }

        Integer lastPosition = productionQueueRepository.findLastPosition();

            if(lastPosition == null) {
                lastPosition = 0;
            }

        ProductionQueue queue = new ProductionQueue();

        queue.setProductionOrder(order);
        queue.setPosition(lastPosition + 1);
        queue.setStatus(QueueAction.WAITING);

        productionQueueRepository.save(queue);
    }
}
