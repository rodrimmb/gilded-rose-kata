package com.gildedrose.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgedBrieItemTest {

    @Test
    void agedBrieIncreasesQualityWhenOlder() {
        AgedBrieItem agedBrie = AgedBrieItem.builder().name("Aged Brie").sellIn(5).quality(1).build();

        agedBrie.update();

        assertEquals("Aged Brie", agedBrie.getName());
        assertEquals(4, agedBrie.getSellIn());
        assertEquals(2, agedBrie.getQuality());
    }

    @Test
    void agedBrieIncreasesQualityBy2WhenOlderInSellIn() {
        AgedBrieItem agedBrie = AgedBrieItem.builder().name("Aged Brie").sellIn(0).quality(1).build();

        agedBrie.update();

        assertEquals("Aged Brie", agedBrie.getName());
        assertEquals(-1, agedBrie.getSellIn());
        assertEquals(3, agedBrie.getQuality());
    }

    @Test
    void agedBrieIncreasesQualityBy2WhenOlderAfterSellIn() {
        AgedBrieItem agedBrie = AgedBrieItem.builder().name("Aged Brie").sellIn(-1).quality(1).build();

        agedBrie.update();

        assertEquals("Aged Brie", agedBrie.getName());
        assertEquals(-2, agedBrie.getSellIn());
        assertEquals(3, agedBrie.getQuality());
    }

    @Test
    void qualityNeverMoreThan50() {
        AgedBrieItem agedBrie = AgedBrieItem.builder().name("Aged Brie").sellIn(5).quality(50).build();

        agedBrie.update();

        assertEquals("Aged Brie", agedBrie.getName());
        assertEquals(4, agedBrie.getSellIn());
        assertEquals(50, agedBrie.getQuality());
    }

}