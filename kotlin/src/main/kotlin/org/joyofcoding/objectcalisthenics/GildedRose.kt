package org.joyofcoding.objectcalisthenics

fun main() {
    val gildedRose = GildedRose()
    gildedRose.updateQuality(gildedRose.makeItems())
}

class GildedRose {

    fun makeItems(): List<Item> {
        val items = mutableListOf<Item>()
        items.add(Item("+5 Dexterity Vest", 10, 20))
        items.add(AgedBrie(2, 0))
        items.add(Item("Elixir of the Mongoose", 5, 7))
        items.add(Sulfuras(0, 80))
        items.add(BackstagePasses(15, 20))
        items.add(Item("Conjured Mana Cake", 3, 6))
        return items
    }

    fun updateQuality(items: List<Item>) {
        for (item in items) {
            item.updateQuality()
        }
    }

}

