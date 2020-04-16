package com.gildedrose.repository;

import com.gildedrose.model.AgedBrieItem;
import com.gildedrose.model.DefaultItem;
import com.gildedrose.model.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryItemRepositoryTest {

    @Test
    void emptyRepositoryWhenItIsCreated() {
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository();

        List<Item> allItems = inMemoryItemRepository.getAllItems();

        assertEquals(0, allItems.size());
    }

    @Test
    void createRepositoryFromList() {
        DefaultItem foo = DefaultItem.builder().name("foo").sellIn(1).quality(8).build();
        DefaultItem bar = DefaultItem.builder().name("bar").sellIn(5).quality(28).build();
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(foo, bar));

        List<Item> allItems = inMemoryItemRepository.getAllItems();

        assertEquals(2, allItems.size());
        assertTrue(allItems.contains(foo));
        assertTrue(allItems.contains(bar));
    }

    @Test
    void canAddItemToEmptyRepository() {
        DefaultItem foo = DefaultItem.builder().name("foo").sellIn(1).quality(8).build();
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository();
        inMemoryItemRepository.addItem(foo);

        List<Item> allItems = inMemoryItemRepository.getAllItems();

        assertEquals(1, allItems.size());
        assertEquals(foo, allItems.get(0));
    }

    @Test
    void canAddItemToRepositoryCreatedFromList() {
        DefaultItem foo = DefaultItem.builder().name("foo").sellIn(1).quality(8).build();
        DefaultItem bar = DefaultItem.builder().name("bar").sellIn(5).quality(28).build();
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(foo, bar));
        AgedBrieItem newItem = AgedBrieItem.builder().name("aged brie").sellIn(14).quality(8).build();
        inMemoryItemRepository.addItem(newItem);

        List<Item> allItems = inMemoryItemRepository.getAllItems();

        assertEquals(3, allItems.size());
        assertTrue(allItems.contains(foo));
        assertTrue(allItems.contains(bar));
        assertTrue(allItems.contains(newItem));
    }
}