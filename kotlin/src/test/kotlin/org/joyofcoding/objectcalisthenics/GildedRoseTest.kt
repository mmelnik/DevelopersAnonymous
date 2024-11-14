package org.joyofcoding.objectcalisthenics

import kotlin.random.Random

import org.joyofcoding.objectcalisthenics.assertions.ItemsAssert
import org.junit.Before
import org.junit.Test

private const val MAX_BACKSTAGE_SELLIN: Int = 30
private const val MAX_QUALITY: Int = 50

internal class GildedRoseTest {

    private lateinit var gildedRose: GildedRose
    private lateinit var items: List<Item>
    private val rand: Random = Random(3456790)

    @Before
    fun setup() {
        gildedRose = GildedRose()
        items = gildedRose.makeItems()
    }

    @Test
    fun after_one_day() {
        repeatUpdateQuality(1)

        ItemsAssert.assertThat(items)
            .containsOnlyItemNames(
                "+5 Dexterity Vest",
                "Aged Brie",
                "Elixir of the Mongoose",
                "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake"
            )
            .containsOnlyItemQualities(19, 1, 6, 80, 21, 5)
            .containsOnlyItemSellIns(9, 1, 4, 0, 14, 2)

    }

    @Test
    fun after_one_day_with_sufuras_having_sellIn_lesser_than_zero_and_quality_greater_than_zero() {
        items = mutableListOf(Item("Sulfuras, Hand of Ragnaros", -1, 1))
        repeatUpdateQuality(1)

        ItemsAssert.assertThat(items)
            .containsOnlyItemNames("Sulfuras, Hand of Ragnaros")
            .containsOnlyItemQualities(1)
            .containsOnlyItemSellIns(-1)
    }

    @Test
    fun after_three_days() {
        repeatUpdateQuality(3)

        ItemsAssert.assertThat(items)
            .containsOnlyItemNames(
                "+5 Dexterity Vest",
                "Aged Brie",
                "Elixir of the Mongoose",
                "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake"
            )
            .containsOnlyItemQualities(17, 4, 4, 80, 23, 3)
            .containsOnlyItemSellIns(7, -1, 2, 0, 12, 0)
    }

    @Test
    fun after_a_shitload_of_days() {
        repeatUpdateQuality(500)

        ItemsAssert.assertThat(items)
            .containsOnlyItemNames(
                "+5 Dexterity Vest",
                "Aged Brie",
                "Elixir of the Mongoose",
                "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake"
            )
            .containsOnlyItemQualities(0, 50, 0, 80, 0, 0)
            .containsOnlyItemSellIns(-490, -498, -495, 0, -485, -497)
    }

    @Test
    fun backstage_pass_golden_copy() {
        items = aBunchOfBackstagePasses()
        repeatUpdateQuality(11)

        ItemsAssert.assertThat(items) //33, 26, 43, 16
            .containsOnlyItemQualities(49, 50, 22, 29, 25, 50, 50, 0, 32, 0, 17, 0, 12, 0, 50, 46, 0, 0, 50, 0, 50, 0,
                11, 47, 0, 22, 50, 0, 0, 22, 39, 15, 0, 0, 0, 50, 35, 0, 48, 50, 0, 14, 0, 19, 17, 21, 24, 50, 0, 0, 0,
                50, 23, 45, 39, 22, 44, 41, 22, 41, 0, 0, 0, 0, 30, 44, 41, 29, 50, 22, 12, 28, 35, 27, 42, 50, 36, 18,
                0, 40, 50, 18, 0, 40, 37, 21, 0, 48, 27, 39, 0, 49, 0, 34, 34, 50, 24, 50, 50, 29, 50)
            .containsOnlyItemSellIns(2, 9, 8, 3, 17, 6, 4, -8, 10, -8, 13, -6, 12, -8, 17, 18, -7, -5, 14, -4, 1, -5,
                16, 9, -10, 13, 5, -10, -6, 5, 12, 13, -9, -7, -1, 18, 12, -4, 17, 11, -6, 9, -7, 17, 16, 9, 5, 3, -1,
                -4, -5, 9, 13, 6, 11, 18, 11, 17, 2, 7, -6, -10, -9, -5, 0, 5, 18, 6, 3, 3, 10, 9, 14, 15, 8, 8, 11, 16,
                -4, 6, 13, 16, -6, 14, 6, 16, -1, 12, 16, 1, -9, 0, -4, 10, 10, 6, 18, 5, 14, 3, 4)
    }

    private fun repeatUpdateQuality(times: Int) {
        for (i in 1..times) {
            gildedRose.updateQuality(items)
        }
    }

    private fun aBunchOfBackstagePasses(): List<Item> {
        val listOfPasses = mutableListOf<Item>()
        for (i in 0..100) {
            listOfPasses.add(aRandomBackstagePass())
        }
        return listOfPasses
    }

    private fun randomSellIn(): Int {
        return rand.nextInt(MAX_BACKSTAGE_SELLIN)
    }

    private fun randomQuality(): Int {
        return rand.nextInt(MAX_QUALITY)
    }

    private fun aRandomBackstagePass(): Item {
        val quality = randomQuality()
        val sellIn = randomSellIn()
        return Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
    }

}