package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int MAX_QUALITY = 50;
    private static final int MINUMUM_QUALITY = 0;

    private static final int BACKSTAGE_INCREMENT_2_QUALITY_LESS_THAN_10_DAYS = 10;
    private static final int BACKSTAGE_INCREMENT_3_QUALITY_LESS_THAN_5_DAYS = 5;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!isLegendaryItem(items[i])) {
                items[i].passDay();
            }

            if (isDecreasedQualityItem(items[i]) && isOverMinimumQuality(items[i])) {
                if (!isLegendaryItem(items[i])) {
                    items[i].decreaseQuality();
                }
            } else {
                if (isLessThanMaximumQuality(items[i])) {
                    items[i].increaseQuality();

                    if (items[i].getName().equals(BACKSTAGE)) {
                        if (items[i].getSellIn() < BACKSTAGE_INCREMENT_2_QUALITY_LESS_THAN_10_DAYS) {
                            if (isLessThanMaximumQuality(items[i])) {
                                items[i].increaseQuality();
                            }
                        }

                        if (items[i].getSellIn() < BACKSTAGE_INCREMENT_3_QUALITY_LESS_THAN_5_DAYS) {
                            if (isLessThanMaximumQuality(items[i])) {
                                items[i].increaseQuality();
                            }
                        }
                    }
                }
            }

            if (items[i].isPastSellInDate()) {
                if (!items[i].getName().equals(AGED_BRIE)) {
                    if (!items[i].getName().equals(BACKSTAGE)) {
                        if (isOverMinimumQuality(items[i])) {
                            if (!isLegendaryItem(items[i])) {
                                items[i].decreaseQuality();
                            }
                        }
                    } else {
                        items[i].dropQualityTo0();
                    }
                } else {
                    if (isLessThanMaximumQuality(items[i])) {
                        items[i].increaseQuality();
                    }
                }
            }
        }
    }

    private boolean isDecreasedQualityItem(Item item) {
        return !item.getName().equals(AGED_BRIE) && !item.getName().equals(BACKSTAGE);
    }

    private boolean isLegendaryItem(Item item) {
        return item.getName().equals(SULFURAS);
    }

    private boolean isLessThanMaximumQuality(Item item) {
        return item.getQuality() < MAX_QUALITY;
    }

    private boolean isOverMinimumQuality(Item item) {
        return item.getQuality() > MINUMUM_QUALITY;
    }
}