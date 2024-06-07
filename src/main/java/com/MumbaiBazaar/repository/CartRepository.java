package com.MumbaiBazaar.repository;

import com.MumbaiBazaar.model.Cart;
import com.MumbaiBazaar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}