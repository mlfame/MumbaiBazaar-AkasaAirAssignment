package com.MumbaiBazaar.service.impl;

import com.MumbaiBazaar.model.Cart;
import com.MumbaiBazaar.model.Item;
import com.MumbaiBazaar.model.User;
import com.MumbaiBazaar.repository.UserRepository;
import com.MumbaiBazaar.service.CartService;
import com.MumbaiBazaar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;


    @Override
    public User registerUser(String email, String password, String role) {
        User user = new User(null, email, password, role, null);
        user = userRepository.save(user);
        Cart cart = new Cart(null, new ArrayList<Item>(), user);
        cartService.createCart(cart);
//        user.setCart(cart);
//        user = userRepository.save(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

