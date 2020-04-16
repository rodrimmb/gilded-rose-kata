package com.gildedrose;

import com.gildedrose.model.Item;
import com.gildedrose.repository.ItemRepository;

class GildedRose {

    private ItemRepository itemRepository;

    public GildedRose(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void updateQuality() {
        itemRepository.getAllItems().forEach(Item::update);
    }
}