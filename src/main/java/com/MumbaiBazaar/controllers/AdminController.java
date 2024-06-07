package com.MumbaiBazaar.controllers;

import com.MumbaiBazaar.dto.Input;
import com.MumbaiBazaar.model.Item;
import com.MumbaiBazaar.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        try {
            Item addedItem = this.itemService.addItem(item);
            if (addedItem != null) {
                String successMessage = String.format("Item '%s' with ID '%d' added successfully",
                        addedItem.getItemName(), addedItem.getItemId());
                return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
            } else {
                String failedMessage = String.format("Item '%s' could not be added",
                        item.toString());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedMessage);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the item");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItem(@RequestBody Input item) {
        try {
            this.itemService.deleteByItemName(item.getItemName());
            return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the item");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStock(@RequestBody Input item) {
        try {
            String itemName = item.getItemName();
            Integer stock = item.getStock();
            Item addedItem = this.itemService.updateStock(itemName, stock);
            if (addedItem != null) {
                String successMessage = String.format("Item '%s' with updated stock '%d'  updated successfully",
                        addedItem.getItemName(), addedItem.getStock());
                return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
            } else {
                String failedMessage = String.format("Item '%s' could not be added with stock",
                        item.toString());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedMessage);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while update the stock of an item");
        }
    }
}
