package com.gildedrose;

public class Item {

    private String name;

    private int sellIn;

    private int quality;

    private Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void increaseQuality() {
        quality += 1;
    }

    public void decreaseQuality() {
        quality -= 1;
    }

    public void dropQualityTo0() {
        quality = 0;
    }

    public void passDay() {
        sellIn -= 1;
    }

    public boolean isPastSellInDate() {
        return sellIn < 0;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int sellIn;
        private String name;
        private int quality;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder sellIn(int sellIn) {
            this.sellIn = sellIn;
            return this;
        }

        public Builder quality(int quality) {
            this.quality = quality;
            return this;
        }

        public Item build() {
            return new Item(name, sellIn, quality);
        }
    }
}
