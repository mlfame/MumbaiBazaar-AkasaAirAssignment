package com.MumbaiBazaar.service.impl;

import com.MumbaiBazaar.model.Item;
import com.MumbaiBazaar.repository.ItemRepository;
import com.MumbaiBazaar.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemsRepository;

    public ItemServiceImpl(ItemRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
    @Override
    public Collection<Item> findByItem(Item currItem) {
        List<Item> itemList = this.itemsRepository.findAll();
        if (currItem.getItemType() != null) {
            itemList = itemList.stream()
                    .filter(item -> item.getItemType() != null && item.getItemType().toLowerCase().contains(currItem.getItemType().toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (currItem.getIsVeg() != null) {
            itemList = itemList.stream()
                    .filter(item -> item.getIsVeg() != null && item.getIsVeg().equals(currItem.getIsVeg()))
                    .collect(Collectors.toList());
        }
        if (currItem.getItemName() != null) {
            itemList = itemList.stream()
                    .filter(item -> item.getIsVeg() != null && item.getItemName().toLowerCase().contains(currItem.getItemName().toLowerCase()))
                    .collect(Collectors.toList());
        }
        return itemList;
    }

    @Override
    public Item findByItemName(String itemName) {
        return this.itemsRepository.findByItemName(itemName);
    }

    @Override
    public Item addItem(Item item) {
        return itemsRepository.save(item);
    }

    @Override
    public void deleteByItemName(String itemName) {
        itemsRepository.deleteByItemName(itemName);
    }

    @Override
    public Item updateStock(String itemName, Integer stock) {
        Item currItem =  this.findByItemName(itemName);
        int updateStock = currItem.getStock()  + stock;
        currItem.setStock(Math.max(updateStock, 0));
        return itemsRepository.save(currItem);
    }
}
