package com.gildedrose.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackstageItemTest {

    @Test
    void backstagePassesIncreasesQualityWhenLeftMoreThan10Days() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(15)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(14, backstageItem.getSellIn());
        assertEquals(9, backstageItem.getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy2WhenLeft10Days() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(10)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(9, backstageItem.getSellIn());
        assertEquals(10, backstageItem.getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy2WhenLeftLessThan10Days() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(8)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(7, backstageItem.getSellIn());
        assertEquals(10, backstageItem.getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy3WhenLeft5Days() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(5)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(4, backstageItem.getSellIn());
        assertEquals(11, backstageItem.getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy3WhenLeftLessThan5Days() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(3)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(2, backstageItem.getSellIn());
        assertEquals(11, backstageItem.getQuality());
    }

    @Test
    void backstagePassesQualityDrops0AfterConcert() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(0)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(-1, backstageItem.getSellIn());
        assertEquals(0, backstageItem.getQuality());
    }

    @Test
    void backstagePassesQualityDrops0AfterConcertPastOneDay() {
        BackstageItem backstageItem = BackstageItem.builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(-1)
                .quality(8)
                .build();

        backstageItem.update();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstageItem.getName());
        assertEquals(-2, backstageItem.getSellIn());
        assertEquals(0, backstageItem.getQuality());
    }

}