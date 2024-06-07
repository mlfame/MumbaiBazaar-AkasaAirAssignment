package com.MumbaiBazaar.service;

import com.MumbaiBazaar.model.Cart;
import com.MumbaiBazaar.model.Item;
import com.MumbaiBazaar.model.User;

import java.util.List;

public interface CartService {
    Cart createCart(Cart cart);
    List<Item> getCartItemsByUser(User user);
    Cart addItemToCart(User user, Item item, int quantity);
    Cart clearCart(User user);
    Integer checkout(User user);
}
