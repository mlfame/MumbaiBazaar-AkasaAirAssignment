package com.MumbaiBazaar.service.impl;

import com.MumbaiBazaar.model.Cart;
import com.MumbaiBazaar.model.Item;
import com.MumbaiBazaar.model.User;
import com.MumbaiBazaar.repository.CartRepository;
import com.MumbaiBazaar.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public List<Item> getCartItemsByUser(User user) {
        Cart cart = this.cartRepository.findByUser(user);
        return cart.getItems();
    }

    @Override
    public Cart addItemToCart(User user, Item item, int quantity) {
        Cart cart = this.cartRepository.findByUser(user);
        System.out.println(cart.getItems());
        this.cartRepository.delete(cart);
        List<Item> items = cart.getItems();
        for(int idx = 0; idx < quantity; idx+=1) {
            items.add(item);
        }
        cart.setItems(items);
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(User user) {
        Cart cart = this.cartRepository.findByUser(user);
        cart.setItems(new ArrayList<Item>());
        return this.cartRepository.save(cart);
    }

    @Override
    public Integer checkout(User user) {
        Cart cart = this.cartRepository.findByUser(user);
        List<Item> items = cart.getItems();
        Integer totalPrice = items.stream()
                .mapToInt(Item::getPrice)
                .sum();
        return totalPrice;
    }
}
