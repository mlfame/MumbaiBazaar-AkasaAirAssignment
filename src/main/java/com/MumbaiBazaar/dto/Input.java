package com.MumbaiBazaar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Input {
    // Users
    private String email;
    private String password;
    private String role;


    //Items
    private String itemName; // Fuji Apple, Mango Alphonso, etc
    private int quantity; // to be deducted for end-user
    private String itemType; // Fruits, Vegetable, etc
    private Boolean isVeg; // is Vegetarian
    private Integer price; // price of each item
    private Integer stock; // Supply stock in your market
}
