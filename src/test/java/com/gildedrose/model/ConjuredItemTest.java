package com.gildedrose.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjuredItemTest {

    @Test
    void qualityDegradesTwoTimesFaster() {
        Item item = ConjuredItem.builder().name("foo").sellIn(5).quality(10).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(4, item.getSellIn());
        assertEquals(8, item.getQuality());
    }

    @Test
    void qualityDegradesTwoTimesFasterAlsoIfIsSellIn() {
        Item item = ConjuredItem.builder().name("foo").sellIn(0).quality(10).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(-1, item.getSellIn());
        assertEquals(6, item.getQuality());
    }

    @Test
    void qualityDegradesTwoTimesFasterAlsoPastSellIn() {
        Item item = ConjuredItem.builder().name("foo").sellIn(-2).quality(10).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(-3, item.getSellIn());
        assertEquals(6, item.getQuality());
    }

}