package com.example.androidappfinalproject



import org.junit.Test
import models.Beers
import org.junit.Assert.*


class BeerListTest {
    val beers = Beers(
        "SomeUID",
        "Beer1",
        "BeerName1",
        "BeerType1"
    )
    val beers2 = Beers(
        "SomeUID2",
        "Beer2",
        "BeerName2",
        "BeerType2"
    )
    val beers3 = Beers(
        "SomeUID3",
        "Beer3",
        "BeerName3",
        "BeerType3"
    )
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