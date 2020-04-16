package com.gildedrose.model;

public class AgedBrieItem extends Item {

    public AgedBrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        passDay();
        increaseQuality();
        if (isPastSellInDate()) {
            increaseQuality();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractItemBuilder<AgedBrieItem, AgedBrieItem.Builder> {

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
        public AgedBrieItem build() {
            return new AgedBrieItem(name, sellIn, quality);
        }
    }
}
