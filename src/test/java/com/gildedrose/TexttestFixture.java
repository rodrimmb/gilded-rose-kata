package com.gildedrose;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = new Item[] {
                Item.builder().name("+5 Dexterity Vest").sellIn(10).quality(20).build(),
                Item.builder().name("Aged Brie").sellIn(2).quality(0).build(),
                Item.builder().name("Elixir of the Mongoose").sellIn(5).quality(7).build(),
                Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(0).quality(80).build(),
                Item.builder().name("Sulfuras, Hand of Ragnaros").sellIn(-1).quality(80).build(),
                Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(15).quality(20).build(),
                Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(10).quality(49).build(),
                Item.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(5).quality(49).build(),
                // this conjured item does not work properly yet
                Item.builder().name("Conjured Mana Cake").sellIn(3).quality(6).build()
        };

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
