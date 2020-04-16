package com.gildedrose.model;

public class ConjuredItem extends Item {

    private final int QUALITY_DEGRADATION_MULTIPLIER = 2;

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        passDay();
        decreaseQuality();
        if (isPastSellInDate()) {
            decreaseQuality();
        }
    }

    @Override
    public void decreaseQuality() {
        for (int i = 0; i < QUALITY_DEGRADATION_MULTIPLIER; i++) {
            super.decreaseQuality();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractItemBuilder<ConjuredItem, ConjuredItem.Builder> {

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
        public ConjuredItem build() {
            return new ConjuredItem(name, sellIn, quality);
        }
    }

}
