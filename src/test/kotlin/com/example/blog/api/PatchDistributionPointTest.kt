package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.InMemoryDb
import com.example.blog.model.entity.DistributionPointModel
import com.example.blog.model.entity.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PatchDistributionPointTest {


    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addDistributionPoints(
            Dummies.makeDummyDistributionPoints(500)
        )
    }

    @Test
    fun testBasic() {
        val patched = DistributionPointModel(
            id = 200,
            name = "공덕역 5번 출구",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        val response = controller.patchDistributionPoint(
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
            InMemoryDb.getDistributionPoints().find { it.id == patched.id },
            response
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIdIsNull() {
        val patched = DistributionPointModel(
            id = null,
            name = "공덕역 5번 출구",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.patchDistributionPoint(
            patched
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCatNotFound() {
        val patched = DistributionPointModel(
            id = 600,
            name = "공덕역 5번 출구",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.patchDistributionPoint(
            patched
        )
    }
}