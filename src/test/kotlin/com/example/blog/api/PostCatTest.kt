package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.InMemoryDb
import com.example.blog.model.entity.CatModel
import com.example.blog.model.entity.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class PostCatTest {


    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addCats(
            Dummies.makeDummyCats(500)
        )
    }

    @Test
    fun testBasic() {
        val inserted = CatModel(
            id = null,
            name = "나비",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        val response = controller.postCat(
            inserted
        ).data

        Assert.assertEquals(
            inserted.name,
            response.name
        )
        Assert.assertEquals(
            inserted.location,
            response.location
        )
        Assert.assertArrayEquals(
            inserted.images,
            response.images
        )
        Assert.assertArrayEquals(
            inserted.feature,
            response.feature
        )
        Assert.assertEquals(
            InMemoryDb.getLastCatId(),
            response.id
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIdIsNotNull() {
        val inserted = CatModel(
            id = 2,
            name = "나비",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.postCat(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNameIsNull() {
        val inserted = CatModel(
            id = null,
            name = null,
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.postCat(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testLocationIsNotNull() {
        val inserted = CatModel(
            id = null,
            name = "나비",
            location = null,
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.postCat(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testImagesIsNotNull() {
        val inserted = CatModel(
            id = 2,
            name = "나비",
            location = Location(20.0, 20.0),
            images = null,
            feature = emptyArray()
        )
        controller.postCat(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testFeatureIsNotNull() {
        val inserted = CatModel(
            id = 2,
            name = "나비",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = null
        )
        controller.postCat(
            inserted
        )
    }
}