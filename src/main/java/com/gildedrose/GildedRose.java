package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int MAX_QUALITY = 50;
    private static final int MINUMUM_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (isDecreasedQualityItem(items[i])) {
                if (isOverMinimumQuality(items[i])) {
                    if (!isLegendaryItem(items[i])) {
                        items[i].decreaseQuality();
                    }
                }
            } else {
                if (isLessThanMaximumQuality(items[i])) {
                    items[i].increaseQuality();

                    if (items[i].getName().equals(BACKSTAGE)) {
                        if (items[i].getSellIn() < 11) {
                            if (isLessThanMaximumQuality(items[i])) {
                                items[i].increaseQuality();
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (isLessThanMaximumQuality(items[i])) {
                                items[i].increaseQuality();
                            }
                        }
                    }
                }
            }

            if (!isLegendaryItem(items[i])) {
                items[i].passDay();
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