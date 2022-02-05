package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.ShoppingCartNotFoundException;
import com.crud.crudstoreserver.models.ShoppingCart;
import com.crud.crudstoreserver.models.PurchaseOrderItem;
import com.crud.crudstoreserver.models.Person;
import com.crud.crudstoreserver.repositories.ShoppingCartRepository;
import com.crud.crudstoreserver.services.ShoppingCartService;
import com.crud.crudstoreserver.services.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PurchaseOrderItemService purchaseOrderItemService;

    @Override
    public ShoppingCart findCartByPerson(Person person) throws ShoppingCartNotFoundException {
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findCartByPerson(person);

        if (cartOptional.isEmpty()) {
            throw new ShoppingCartNotFoundException(person);
        }

        return cartOptional.get();
    }

    @Override
    public void createCartByPerson(Person person) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPerson(person);
        shoppingCart.setActive(true);
        shoppingCartRepository.save(shoppingCart);
    }


    @Override
    public ShoppingCart updateCartByPerson(Person person) throws ShoppingCartNotFoundException {
        List<PurchaseOrderItem> purchaseOrderItemList = purchaseOrderItemService.findAllActiveOrderItemsByPerson(person);
        ShoppingCart shoppingCart = findCartByPerson(person);
        shoppingCart.setPurchaseOrderItems(purchaseOrderItemList);
        shoppingCart.setTotalCost(BigDecimal.ZERO);
        purchaseOrderItemList.forEach(orderItem -> shoppingCart.setTotalCost(shoppingCart.getTotalCost().add(orderItem.getTotalPrice())));
        shoppingCartRepository.saveAndFlush(shoppingCart);
        return shoppingCart;
    }

    @Override
    public void deleteCartByPersons(Person person) throws ShoppingCartNotFoundException {
        Optional<ShoppingCart> cartOptional = Optional.ofNullable(findCartByPerson(person));

        if (cartOptional.isEmpty()){
            throw new ShoppingCartNotFoundException(person);
        } else {
            ShoppingCart shoppingCart = cartOptional.get();
            shoppingCart.setActive(false);
            shoppingCartRepository.saveAndFlush(shoppingCart);
        }
    }

    @Override
    public void restoreCartByPerson(Person person) throws ShoppingCartNotFoundException {
        Optional<ShoppingCart> cartOptional = Optional.ofNullable(findCartByPerson(person));

        if (cartOptional.isEmpty()){
            throw new ShoppingCartNotFoundException(person);
        } else {
            ShoppingCart shoppingCart = cartOptional.get();
            shoppingCart.setActive(true);
            shoppingCartRepository.saveAndFlush(shoppingCart);
        }
    }

}
