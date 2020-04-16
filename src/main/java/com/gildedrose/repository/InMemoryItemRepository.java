package com.gildedrose.repository;

import com.gildedrose.model.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryItemRepository implements ItemRepository {

    private final List<Item> items = new ArrayList<>();

    public InMemoryItemRepository() { }

    public InMemoryItemRepository(List<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public List<Item> getAllItems() {
        return items;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }
}
