package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.PurchaseOrderItemNotFoundException;
import com.crud.crudstoreserver.models.PurchaseOrderItem;
import com.crud.crudstoreserver.models.Product;
import com.crud.crudstoreserver.models.Person;
import com.crud.crudstoreserver.repositories.PurchaseOrderItemRepository;
import com.crud.crudstoreserver.services.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Override
    public List<PurchaseOrderItem> findAllOrderItems() {
        return purchaseOrderItemRepository.findAll();
    }

    @Override
    public PurchaseOrderItem findById(Long id) throws PurchaseOrderItemNotFoundException {
        Optional<PurchaseOrderItem> orderItemOptional = purchaseOrderItemRepository.findById(id);

        if(orderItemOptional.isEmpty()) {
            throw new PurchaseOrderItemNotFoundException(id);
        }

        return orderItemOptional.get();
    }

    @Override
    public PurchaseOrderItem findByProduct(Product product) throws PurchaseOrderItemNotFoundException {
        Optional<PurchaseOrderItem> orderItemOptional = purchaseOrderItemRepository.findByProduct(product);

        if(orderItemOptional.isEmpty()) {
            throw new PurchaseOrderItemNotFoundException(product);
        }

        return orderItemOptional.get();
    }

    @Override
    public void updateOrderItem(PurchaseOrderItem purchaseOrderItem) throws PurchaseOrderItemNotFoundException {
        if(!purchaseOrderItemRepository.existsById(purchaseOrderItem.getId())) {
            throw new PurchaseOrderItemNotFoundException(purchaseOrderItem.getId());
        }

        purchaseOrderItemRepository.saveAndFlush(purchaseOrderItem);
    }

    @Override
    public void deleteOrderItemById(Long id) throws PurchaseOrderItemNotFoundException {
        Optional<PurchaseOrderItem> orderItemOptional = Optional.ofNullable(findById(id));

        if(orderItemOptional.isEmpty()) {
            throw new PurchaseOrderItemNotFoundException(id);
        } else {
            PurchaseOrderItem purchaseOrderItem = orderItemOptional.get();
            purchaseOrderItem.setActive(false);
            purchaseOrderItemRepository.saveAndFlush(purchaseOrderItem);
        }
    }

    @Override
    public void restoreOrderItemById(Long id) throws PurchaseOrderItemNotFoundException {
        Optional<PurchaseOrderItem> orderItemOptional = Optional.ofNullable(findById(id));

        if(orderItemOptional.isEmpty()) {
            throw new PurchaseOrderItemNotFoundException(id);
        } else {
            PurchaseOrderItem purchaseOrderItem = orderItemOptional.get();
            purchaseOrderItem.setActive(true);
            purchaseOrderItemRepository.saveAndFlush(purchaseOrderItem);
        }
    }

    @Override
    public List<PurchaseOrderItem> findAllActiveOrderItemsByPerson(Person person) {
        return purchaseOrderItemRepository.findAllByPerson(person).stream()
                .filter(PurchaseOrderItem::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void createOrderItem(PurchaseOrderItem purchaseOrderItem) {
        purchaseOrderItem.setActive(true);
        purchaseOrderItemRepository.save(purchaseOrderItem);
    }
}
