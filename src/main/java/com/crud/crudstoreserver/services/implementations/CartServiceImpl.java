package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.CartNotFoundException;
import com.crud.crudstoreserver.models.Cart;
import com.crud.crudstoreserver.models.OrderItem;
import com.crud.crudstoreserver.models.Users;
import com.crud.crudstoreserver.repositories.CartRepository;
import com.crud.crudstoreserver.services.CartService;
import com.crud.crudstoreserver.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public Cart findCartByUser(Users user) throws CartNotFoundException {
        Optional<Cart> cartOptional = cartRepository.findCartByUser(user);

        if (cartOptional.isEmpty()) {
            throw new CartNotFoundException(user);
        }

        return cartOptional.get();
    }

    @Override
    public void createCartByUser(Users user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setActive(true);
        cartRepository.save(cart);
    }


    @Override
    public Cart updateCartByUser(Users user) throws CartNotFoundException {
        List<OrderItem> orderItemList = orderItemService.findAllActiveOrderItemsByUser(user);
        Cart cart = findCartByUser(user);
        cart.setOrderItems(orderItemList);
        cart.setTotalCost(BigDecimal.ZERO);
        orderItemList.forEach(orderItem -> cart.setTotalCost(cart.getTotalCost().add(orderItem.getTotalPrice())));
        cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public void deleteCartByUsers(Users user) throws CartNotFoundException {
        Optional<Cart> cartOptional = Optional.ofNullable(findCartByUser(user));

        if (cartOptional.isEmpty()){
            throw new CartNotFoundException(user);
        } else {
            Cart cart = cartOptional.get();
            cart.setActive(false);
            cartRepository.saveAndFlush(cart);
        }
    }

    @Override
    public void restoreCartByUser(Users user) throws CartNotFoundException {
        Optional<Cart> cartOptional = Optional.ofNullable(findCartByUser(user));

        if (cartOptional.isEmpty()){
            throw new CartNotFoundException(user);
        } else {
            Cart cart = cartOptional.get();
            cart.setActive(true);
            cartRepository.saveAndFlush(cart);
        }
    }

}
