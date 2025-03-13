package org.joyofcoding.objectcalisthenics

open class Item(var name: String, var sellIn: Int, var quality: Int) {

    open fun updateQuality() {
        if (name == "Backstage passes to a TAFKAL80ETC concert") {
            increaseQuality()

            if (sellIn < 11) {
                increaseQuality()
            }

            if (sellIn < 6) {
                increaseQuality()
            }

            decreaseSellIn()

            if (sellIn < 0) {
                quality = 0
            }
        } else if (name == "Sulfuras, Hand of Ragnaros") {
            // NOP
        } else {
            decreaseQuality()

            decreaseSellIn()

            if (sellIn < 0) {
                decreaseQuality()
            }
        }
    }

    fun increaseQuality() {
        if (quality < 50) {
            quality++
        }
    }

    fun decreaseQuality() {
        if (quality > 0) {
            quality--
        }
    }

    fun decreaseSellIn() {
        sellIn--
    }

    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}

class AgedBrie(sellIn: Int, quality: Int) : Item("Aged Brie", sellIn, quality) {

    override fun updateQuality() {
        increaseQuality()
        decreaseSellIn()
        if (sellIn < 0) {
            increaseQuality()
        }
    }

}