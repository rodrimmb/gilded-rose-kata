package com.gildedrose.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultItemTest {

    @Test
    void qualityDegrades() {
        DefaultItem item = DefaultItem.builder().name("foo").sellIn(5).quality(10).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(4, item.getSellIn());
        assertEquals(9, item.getQuality());
    }

    @Test
    void sellDateHasPassedQualityDegradesTwiceFast() {
        DefaultItem item = DefaultItem.builder().name("foo").sellIn(0).quality(10).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(-1, item.getSellIn());
        assertEquals(8, item.getQuality());
    }

    @Test
    void sellDateIsNegativeQualityDegradesTwiceFast() {
        DefaultItem item = DefaultItem.builder().name("foo").sellIn(-1).quality(10).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(-2, item.getSellIn());
        assertEquals(8, item.getQuality());
    }

    @Test
    void theQualityIsNeverNegative() {
        DefaultItem item = DefaultItem.builder().name("foo").sellIn(0).quality(0).build();

        item.update();

        assertEquals("foo", item.getName());
        assertEquals(-1, item.getSellIn());
        assertEquals(0, item.getQuality());
    }
}