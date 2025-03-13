package org.joyofcoding.objectcalisthenics

open class Item(var name: String, var sellIn: Int, var quality: Int) {

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