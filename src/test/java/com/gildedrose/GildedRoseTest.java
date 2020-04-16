package com.gildedrose;

import com.gildedrose.model.*;
import com.gildedrose.repository.InMemoryItemRepository;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { DefaultItem.builder().name("foo").sellIn(0).quality(0).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);
        app.updateQuality();
        assertEquals("foo", inMemoryItemRepository.getAllItems().get(0).getName());
    }

    @Test
    void qualityDegrades() {
        Item[] items = new Item[] { DefaultItem.builder().name("foo").sellIn(5).quality(10).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("foo", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(4, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(9, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void sellDateHasPassedQualityDegradesTwiceFast() {
        Item[] items = new Item[] { DefaultItem.builder().name("foo").sellIn(0).quality(10).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("foo", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(-1, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(8, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void sellDateIsNegativeQualityDegradesTwiceFast() {
        Item[] items = new Item[] { DefaultItem.builder().name("foo").sellIn(-1).quality(10).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("foo", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(-2, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(8, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void theQualityIsNeverNegative() {
        Item[] items = new Item[] { DefaultItem.builder().name("foo").sellIn(0).quality(0).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("foo", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(-1, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(0, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void agedBrieIncreasesQualityWhenOlder() {
        Item[] items = new Item[] { AgedBrieItem.builder().name("Aged Brie").sellIn(5).quality(1).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Aged Brie", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(4, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(2, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void qualityNeverMoreThan50() {
        Item[] items = new Item[] { AgedBrieItem.builder().name("Aged Brie").sellIn(5).quality(50).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Aged Brie", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(4, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(50, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void sulfurasNeverDecreasesQuality() {
        Item[] items = new Item[] { SulfurasItem.builder().name("Sulfuras, Hand of Ragnaros").sellIn(5).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(5, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(8, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityWhenLeftMoreThan10Days() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(15).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(14, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(9, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy2WhenLeft10Days() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(10).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(9, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(10, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy2WhenLeftLessThan10Days() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(8).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(7, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(10, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy3WhenLeft5Days() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(5).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(4, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(11, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesIncreasesQualityBy3WhenLeftLessThan5Days() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(3).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(2, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(11, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesQualityDrops0AfterConcert() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(0).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(-1, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(0, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }

    @Test
    void backstagePassesQualityDrops0AfterConcertPastOneDay() {
        Item[] items = new Item[] { BackstageItem.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(-1).quality(8).build() };
        InMemoryItemRepository inMemoryItemRepository = new InMemoryItemRepository(asList(items));
        GildedRose app = new GildedRose(inMemoryItemRepository);

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", inMemoryItemRepository.getAllItems().get(0).getName());
        assertEquals(-2, inMemoryItemRepository.getAllItems().get(0).getSellIn());
        assertEquals(0, inMemoryItemRepository.getAllItems().get(0).getQuality());
    }
}
