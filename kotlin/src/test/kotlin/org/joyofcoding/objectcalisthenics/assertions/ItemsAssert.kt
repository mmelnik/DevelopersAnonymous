package org.joyofcoding.objectcalisthenics.assertions

import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.extractProperty
import org.joyofcoding.objectcalisthenics.Item

open class ItemsAssert protected constructor(actual: Iterable<Item>) :
    AbstractIterableAssert<ItemsAssert, Iterable<Item>, Item>(actual, ItemsAssert::class.java) {

    fun containsOnlyItemNames(vararg names: String): ItemsAssert {
        isNotNull()

        val actualItemNames: Iterable<String> = extractProperty("name", String::class.java).from(actual)
        Assertions.assertThat(actualItemNames).containsOnly(*names)

        return this
    }

    fun containsOnlyItemQualities(vararg qualities: Int): ItemsAssert {
        isNotNull()

        val actualItemQualities = extractProperty("quality").from(actual)
        Assertions.assertThat(actualItemQualities).containsOnly(*qualities.toTypedArray())

        return this
    }

    fun containsOnlyItemSellIns(vararg sellIns: Int): ItemsAssert {
        isNotNull();

        val actualItemSellIns = extractProperty("sellIn").from(actual)
        Assertions.assertThat(actualItemSellIns).containsOnly(*sellIns.toTypedArray())

        return this
    }

    companion object {
        fun assertThat(actual: Iterable<Item>): ItemsAssert {
            return ItemsAssert(actual)
        }
    }
}