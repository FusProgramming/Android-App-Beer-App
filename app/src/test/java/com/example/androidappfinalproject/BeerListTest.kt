package com.example.androidappfinalproject

import org.junit.Test
import models.Beers
import org.junit.Assert.*

//--------------------------------------------------------------------------------------------------
class BeerListTest {
    val beers = Beers(
        "SomeUID",
        "Two Roads",
        "Two Evil",
        "IPA"
    )
    val beers2 = Beers(
        "SomeUID2",
        "HOAX BREWING",
        "Seany Palmer",
        "Double IPA"
    )
    val beers3 = Beers(
        "SomeUID3",
        "New England Brewing Company",
        "Fuzzy Baby Ducks",
        "IPA"
    )

//--------------------------------------------------------------------------------------------------
    @Test
    fun beerList() {
        val beerList: MutableList<Beers> = mutableListOf(
            beers,
            beers2,
            beers3
        )
        val beerList2: MutableList<Beers> = mutableListOf(
            beers,
            beers2,
            beers3
        )
        assertEquals(beerList.toList(), beerList2.toList())
    }
}