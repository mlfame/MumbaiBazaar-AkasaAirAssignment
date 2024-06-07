//package com.MumbaiBazaar.controllers;
//
//import com.MumbaiBazaar.dto.Input;
//import com.MumbaiBazaar.model.Cart;
//import com.MumbaiBazaar.model.Item;
//import com.MumbaiBazaar.model.User;
//import com.MumbaiBazaar.service.CartService;
//import com.MumbaiBazaar.service.ItemService;
//import com.MumbaiBazaar.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private ItemService itemService;
//
//    @Autowired
//    private UserService userService;
//
//    public CartController(CartService cartService, ItemService itemService, UserService userService) {
//        this.cartService = cartService;
//        this.itemService = itemService;
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/items")
//    public List<Item> getCartItems(@RequestBody User input) {
//        User user = userService.findByEmail(input.getEmail()).orElse(null);
//        return cartService.getCartItemsByUser(user);
//    }
//
//    @PostMapping("/add")
//    public Cart addItemToCart(@RequestBody Input input) {
//        User user = userService.findByEmail(input.getEmail()).orElse(null);
//        Item item = itemService.findByItemName(input.getItemName());
//        return cartService.addItemToCart(user, item, input.getQuantity());
//    }
//
//    @GetMapping("/checkout")
//    public String checkout(@RequestBody User input) {
//        User user = userService.findByEmail(input.getEmail()).orElse(null);
//        boolean success = cartService.checkout(user);
//        return success ? "Checkout successful" : "One or more items are not available in the required quantity";
//    }
//}
