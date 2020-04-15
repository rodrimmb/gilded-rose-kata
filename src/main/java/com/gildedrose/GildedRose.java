package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].getName().equals(AGED_BRIE)
                    && !items[i].getName().equals(BACKSTAGE)) {
                if (items[i].getQuality() > 0) {
                    if (!items[i].getName().equals(SULFURAS)) {
                        items[i].decreaseQuality();
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].increaseQuality();

                    if (items[i].getName().equals(BACKSTAGE)) {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].increaseQuality();
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].increaseQuality();
                            }
                        }
                    }
                }
            }

            if (!items[i].getName().equals(SULFURAS)) {
                items[i].passDay();
            }

            if (items[i].getSellIn() < 0) {
                if (!items[i].getName().equals(AGED_BRIE)) {
                    if (!items[i].getName().equals(BACKSTAGE)) {
                        if (items[i].getQuality() > 0) {
                            if (!items[i].getName().equals(SULFURAS)) {
                                items[i].decreaseQuality();
                            }
                        }
                    } else {
                        items[i].dropQualityTo0();
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].increaseQuality();
                    }
                }
            }
        }
    }
}