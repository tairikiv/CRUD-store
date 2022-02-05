package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.PurchaseOrderNotFoundException;
import com.crud.crudstoreserver.models.PurchaseOrder;
import com.crud.crudstoreserver.repositories.PurchaseOrderRepository;
import com.crud.crudstoreserver.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
   @Autowired
   private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> findAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder findById(Long id) throws PurchaseOrderNotFoundException {
        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            throw new PurchaseOrderNotFoundException(id);
        }
        return orderOptional.get();
    }

    @Override
    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setActive(true);
        return purchaseOrderRepository.save(purchaseOrder);
    }


    @Override
    public void updateOrder(PurchaseOrder purchaseOrder) throws PurchaseOrderNotFoundException {
        if(!purchaseOrderRepository.existsById(purchaseOrder.getId())) {
            throw new PurchaseOrderNotFoundException(purchaseOrder.getId());
        }
        purchaseOrderRepository.saveAndFlush(purchaseOrder);
    }

    @Override
    public void deleteOrderById(Long id) throws PurchaseOrderNotFoundException {
        Optional<PurchaseOrder> orderOptional = Optional.ofNullable(findById(id));

        if (orderOptional.isEmpty()) {
            throw new PurchaseOrderNotFoundException(id);
        } else {
            PurchaseOrder purchaseOrder = orderOptional.get();
            purchaseOrder.setActive(false);
            purchaseOrderRepository.saveAndFlush(purchaseOrder);
        }
    }

    @Override
    public void restoreOrderById(Long id) throws PurchaseOrderNotFoundException {
        Optional<PurchaseOrder> orderOptional = Optional.ofNullable(findById(id));

        if (orderOptional.isEmpty()) {
            throw new PurchaseOrderNotFoundException(id);
        } else {
            PurchaseOrder purchaseOrder = orderOptional.get();
            purchaseOrder.setActive(true);
            purchaseOrderRepository.saveAndFlush(purchaseOrder);
        }
    }
}
