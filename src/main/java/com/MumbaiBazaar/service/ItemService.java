package com.MumbaiBazaar.service;

import com.MumbaiBazaar.model.Item;

import java.util.Collection;

public interface ItemService {
    Item findByItemName(String itemName);
    Item addItem(Item item);
    void deleteByItemName(String itemName);
    Item updateStock(String itemName, Integer stock);
    Collection<Item> findByItem(Item currItem);
}
