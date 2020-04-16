package com.gildedrose;

import com.gildedrose.model.Item;
import com.gildedrose.repository.ItemRepository;

import java.util.List;

class GildedRose {

    private ItemRepository itemRepository;

    public GildedRose(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void updateQuality() {
        List<Item> items = itemRepository.getAllItems();
        for (Item item : items) {
            item.update();
        }
    }
}