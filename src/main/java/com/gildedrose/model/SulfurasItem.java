package com.gildedrose.model;

public class SulfurasItem extends Item {

    public SulfurasItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        // being a legendary item, never has to be sold or decreases in Quality
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractItemBuilder<SulfurasItem, SulfurasItem.Builder> {

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
        public SulfurasItem build() {
            return new SulfurasItem(name, sellIn, quality);
        }
    }
}
