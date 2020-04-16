package com.gildedrose.repository;

import com.gildedrose.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();
    void addItem(Item item);
}
