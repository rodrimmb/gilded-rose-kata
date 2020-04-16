package com.gildedrose.model;

public class BackstageItem extends Item {

    private static final int BACKSTAGE_INCREMENT_2_QUALITY_LESS_THAN_10_DAYS = 10;
    private static final int BACKSTAGE_INCREMENT_3_QUALITY_LESS_THAN_5_DAYS = 5;

    public BackstageItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        passDay();
        if (isLessThanMaximumQuality()) {
            increaseQuality();
            if (getSellIn() < BACKSTAGE_INCREMENT_2_QUALITY_LESS_THAN_10_DAYS) {
                increaseQuality();
            }
            if (getSellIn() < BACKSTAGE_INCREMENT_3_QUALITY_LESS_THAN_5_DAYS) {
                increaseQuality();
            }
        }
        if (isPastSellInDate()) {
            dropQualityTo0();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractItemBuilder<BackstageItem, BackstageItem.Builder> {

        private String name;
        private int sellIn;
        private int quality;

        @Override
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder sellIn(int sellIn) {
            this.sellIn = sellIn;
            return this;
        }

        @Override
        public Builder quality(int quality) {
            this.quality = quality;
            return this;
        }

        @Override
        public BackstageItem build() {
            return new BackstageItem(name, sellIn, quality);
        }
    }
}
