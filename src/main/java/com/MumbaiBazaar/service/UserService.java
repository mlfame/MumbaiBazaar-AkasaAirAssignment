package com.MumbaiBazaar.service;

import com.MumbaiBazaar.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(String email, String password, String role);
    Optional<User> findByEmail(String email);
}
