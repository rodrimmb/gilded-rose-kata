package com.gildedrose.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SulfurasItemTest {

    @Test
    void sulfurasNeverChangeQualityOrSellIn() {
        SulfurasItem sulfurasItem = SulfurasItem.builder()
                .name("Sulfuras, Hand of Ragnaros")
                .sellIn(5)
                .quality(8)
                .build();

        sulfurasItem.update();

        assertEquals("Sulfuras, Hand of Ragnaros",sulfurasItem.getName());
        assertEquals(5, sulfurasItem.getSellIn());
        assertEquals(8, sulfurasItem.getQuality());
    }

    @Test
    void sulfurasNeverChangeQualityOrSellInEvenInSellIn() {
        SulfurasItem sulfurasItem = SulfurasItem.builder()
                .name("Sulfuras, Hand of Ragnaros")
                .sellIn(0)
                .quality(8)
                .build();

        sulfurasItem.update();

        assertEquals("Sulfuras, Hand of Ragnaros",sulfurasItem.getName());
        assertEquals(0, sulfurasItem.getSellIn());
        assertEquals(8, sulfurasItem.getQuality());
    }

    @Test
    void sulfurasNeverChangeQualityOrSellInEvenPastSellIn() {
        SulfurasItem sulfurasItem = SulfurasItem.builder()
                .name("Sulfuras, Hand of Ragnaros")
                .sellIn(-3)
                .quality(8)
                .build();

        sulfurasItem.update();

        assertEquals("Sulfuras, Hand of Ragnaros",sulfurasItem.getName());
        assertEquals(-3, sulfurasItem.getSellIn());
        assertEquals(8, sulfurasItem.getQuality());
    }

}