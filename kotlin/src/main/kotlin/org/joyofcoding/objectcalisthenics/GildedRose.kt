package org.joyofcoding.objectcalisthenics

fun main() {
    val gildedRose = GildedRose()
    gildedRose.updateQuality(gildedRose.makeItems())
}

class GildedRose {

    fun makeItems(): List<Item> {
        val items = mutableListOf<Item>()
        items.add(Item("+5 Dexterity Vest", 10, 20))
        items.add(Item("Aged Brie", 2, 0))
        items.add(Item("Elixir of the Mongoose", 5, 7))
        items.add(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        items.add(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20))
        items.add(Item("Conjured Mana Cake", 3, 6))
        return items
    }

    fun updateQuality(items: List<Item>) {
        for (item in items) {
            if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality > 0) {
                    if (item.name != "Sulfuras, Hand of Ragnaros") {
                        decreaseQuality(item)
                    }
                }
            } else {
                if (item.quality < 50) {
                    increaseQuality(item)

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            increaseQuality(item)
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item)
                        }
                    }
                }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                decreaseSellIn(item)
            }

            if (item.sellIn < 0) {
                if (item.name != "Aged Brie") {
                    if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.quality > 0) {
                            if (item.name != "Sulfuras, Hand of Ragnaros") {
                                decreaseQuality(item)
                            }
                        }
                    } else {
                        item.quality = 0
                    }
                } else {
                    increaseQuality(item)
                }
            }
        }
    }

    private fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }

    private fun decreaseQuality(item: Item) {
        item.quality = item.quality - 1
    }

    private fun decreaseSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

}

