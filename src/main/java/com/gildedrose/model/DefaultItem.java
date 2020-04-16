package com.gildedrose.model;

public class DefaultItem extends Item {

    public DefaultItem(String name, int sellIn, int quality) {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractItemBuilder<DefaultItem, DefaultItem.Builder> {

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
        public DefaultItem build() {
            return new DefaultItem(name, sellIn, quality);
        }
    }
}
