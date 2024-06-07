package com.MumbaiBazaar.repository;

import com.MumbaiBazaar.model.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Item findByItemName(String itemName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM item WHERE item_name = ?1", nativeQuery = true)
    void deleteByItemName(String itemName);
}