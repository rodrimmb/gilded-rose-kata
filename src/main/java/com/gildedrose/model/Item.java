package com.gildedrose.model;

public abstract class Item {

    private static final int MAX_QUALITY = 50;
    private static final int MINUMUM_QUALITY = 0;

    private String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality){
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
        if (isLessThanMaximumQuality()) {
            quality += 1;
        }
    }

    public void decreaseQuality() {
        if(isOverMinimumQuality()) {
            quality -= 1;
        }
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

    public abstract void update();

    public boolean isLessThanMaximumQuality() {
        return getQuality() < MAX_QUALITY;
    }

    public boolean isOverMinimumQuality() {
        return getQuality() > MINUMUM_QUALITY;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public abstract static class AbstractItemBuilder<T extends Item, E> {

        public abstract E name(String name);

        public abstract E sellIn(int sellIn);

        public abstract E quality(int quality);

        public abstract T build();
    }
}
