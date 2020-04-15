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
        for (Item item : items) {
            if (!isLegendaryItem(item)) {
                item.passDay();
            }

            switch (item.getName()) {
                case AGED_BRIE:
                    updateAgedBrieQuality(item);
                    break;
                case BACKSTAGE:
                    updateBackstageQuality(item);
                    break;
                case SULFURAS:
                    break;
                default:
                    updateQualityAsDefault(item);
            }
        }
    }

    private void updateQualityAsDefault(Item item) {
        if(isOverMinimumQuality(item)) {
            item.decreaseQuality();
        }
        if (item.isPastSellInDate() && isOverMinimumQuality(item)) {
            item.decreaseQuality();
        }
    }

    private void updateBackstageQuality(Item item) {
        if (isLessThanMaximumQuality(item)) {
            item.increaseQuality();

            if (item.getName().equals(BACKSTAGE)) {
                if (item.getSellIn() < BACKSTAGE_INCREMENT_2_QUALITY_LESS_THAN_10_DAYS) {
                    if (isLessThanMaximumQuality(item)) {
                        item.increaseQuality();
                    }
                }

                if (item.getSellIn() < BACKSTAGE_INCREMENT_3_QUALITY_LESS_THAN_5_DAYS) {
                    if (isLessThanMaximumQuality(item)) {
                        item.increaseQuality();
                    }
                }
            }
        }

        if (item.isPastSellInDate()) {
            item.dropQualityTo0();
        }
    }

    private void updateAgedBrieQuality(Item item) {
        if (isLessThanMaximumQuality(item)) {
            item.increaseQuality();
        }
        if (item.isPastSellInDate() && isLessThanMaximumQuality(item)) {
            item.increaseQuality();
        }
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