package com.MumbaiBazaar.controllers;

import com.MumbaiBazaar.dto.Input;
import com.MumbaiBazaar.model.Cart;
import com.MumbaiBazaar.model.Item;
import com.MumbaiBazaar.model.User;
import com.MumbaiBazaar.service.CartService;
import com.MumbaiBazaar.service.ItemService;
import com.MumbaiBazaar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private ItemService itemService;

    private CartService cartService;

    public UserController(UserService userService, ItemService itemService, CartService cartService) {
        this.userService = userService;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody Input user) {
        try {
            User addedUser  = this.userService.registerUser(user.getEmail(), user.getPassword(), user.getRole());
            if (addedUser != null) {
                String successMessage = String.format("User's email '%s' with ID '%d' added successfully",
                        addedUser.getEmail(), addedUser.getUserId());
                return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);

            } else {
                String failedMessage = String.format("User '%s' could not be added",
                        user.getEmail());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedMessage);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the item");
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Collection<Item>> getAll(@RequestBody Item item) {
        try {
            Collection<Item> items = this.itemService.findByItem(item);
            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(items);
            } else {
                return ResponseEntity.ok(items);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/cart/items")
    public ResponseEntity<List<Map<String, Object>>> getCartItems(@RequestBody Input input) {
        try {
            User user = userService.findByEmail(input.getEmail()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            List<Item> items = cartService.getCartItemsByUser(user);
            List<Map<String, Object>> filteredItems = items.stream()
                    .map(item -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("itemName", item.getItemName());
                        map.put("itemType", item.getItemType());
                        map.put("isVeg", item.getIsVeg());
                        map.put("price", item.getPrice());
                        return map;
                    })
                    .collect(Collectors.toList());

            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filteredItems);
            } else {
                return ResponseEntity.ok(filteredItems);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addItemToCart(@RequestBody Input input) {
        try {
            User user = userService.findByEmail(input.getEmail()).orElse(null);
            Item item = itemService.findByItemName(input.getItemName());
            Cart cart = cartService.addItemToCart(user, item, input.getQuantity());
            if (cart != null) {
                String successMessage = String.format("An item '%s' is added to user email '%s' added successfully",
                        input.getItemName(), input.getEmail());
                return ResponseEntity.status(HttpStatus.CREATED).body(cart.toString() + " " + successMessage);
            }
            else {
                String failedMessage = String.format("An item '%s' could not be added",
                        input.getItemName());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedMessage);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/cart/checkout")
    public ResponseEntity<String> checkout(@RequestBody Input input) {
        try {
            User user = userService.findByEmail(input.getEmail()).orElse(null);
            Integer amount = cartService.checkout(user);
            if (amount > 0) {
                String successMessage = String.format("An user email '%s' cart with '%d' is checkout successfully",
                        input.getEmail(), amount);
                return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
            }
            else {
                String failedMessage = String.format("An internal issue observed on user email '%s' cart",
                        input.getEmail());
                return ResponseEntity.status(HttpStatus.CREATED).body(failedMessage);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error while checkout");
        }
    }
}
