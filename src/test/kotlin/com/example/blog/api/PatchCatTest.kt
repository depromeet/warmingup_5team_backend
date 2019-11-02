package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.InMemoryDb
import com.example.blog.model.entity.CatModel
import com.example.blog.model.entity.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PatchCatTest {


    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addCats(
            Dummies.makeDummyCats(500)
        )
    }

    @Test
    fun testBasic() {
        val patched = CatModel(
            id = 200,
            name = "나비",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        val response = controller.patchCat(
            patched
        ).data

        Assert.assertEquals(
            patched.name,
            response.name
        )
        Assert.assertEquals(
            patched.location,
            response.location
        )
        Assert.assertArrayEquals(
            patched.images,
            response.images
        )
        Assert.assertArrayEquals(
            patched.feature,
            response.feature
        )
        Assert.assertEquals(
            InMemoryDb.getCats().find { it.id == patched.id },
            response
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIdIsNull() {
        val patched = CatModel(
            id = null,
            name = "나비",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.patchCat(
            patched
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCatNotFound() {
        val patched = CatModel(
            id = 600,
            name = null,
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.patchCat(
            patched
        )
    }
}