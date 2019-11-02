package com.example.blog

import com.example.blog.model.entity.*
import java.time.ZonedDateTime
import java.util.*

object Dummies {

    private val random = Random()

    fun makeDummySearchWord(number: Int) = SearchWord(
            text ="Hi I'm ${random}th",
            createdAt = ZonedDateTime.now()
    )

    fun makeDummySearchWords(size: Int) = (0..size + 1).map(::makeDummySearchWord).toTypedArray()

    fun makeDummyCat(id: Int) = Cat(
            id = id,
            name = "Cat $id",
            location = makeDummyLocation(),
            images = emptyArray(),
            feature = emptyArray(),
            createdAt = ZonedDateTime.now()
    )

    fun makeDummyCats(size: Int) = (0..size + 1).map(::makeDummyCat).toTypedArray()

    fun makeDummyDistributionPoint(id: Int) = DistributionPoint(
            id = id,
            name = "DistributionPoint $id",
            location = makeDummyLocation(),
            images = emptyArray(),
            feature = emptyArray(),
            createdAt = ZonedDateTime.now()
    )

    fun makeDummyDistributionPoints(size: Int) = (0..size + 1).map(::makeDummyDistributionPoint).toTypedArray()

    fun makeDummyHospital(id: Int) = Hospital(
            name = "Hospital $id",
            location = makeDummyLocation(),
            openTime = "${String.format("%02d", random.nextInt(12))}:${random.nextInt(60)}",
            closeTime = "${String.format("%02d", random.nextInt() + 12)}:${random.nextInt(60)}"
    )

    fun makeDummyHospitals(size: Int) = (0..size + 1).map(::makeDummyHospital).toTypedArray()

    fun makeDummyShelter(id: Int) = Shelter(
            name = "Shelter $id",
            location = makeDummyLocation(),
            openTime = "${String.format("%02d", random.nextInt(12))}:${random.nextInt(60)}",
            closeTime = "${String.format("%02d", random.nextInt() + 12)}:${random.nextInt(60)}"
    )

    fun makeDummyShelters(size: Int) = (0..size + 1).map(::makeDummyShelter).toTypedArray()

    fun makeDummyLocation(
            x: Double = random.nextDouble(),
            y: Double = random.nextDouble()
    ) = Location(x, y)
}