package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.InMemoryDb
import com.example.blog.model.entity.CatModel
import com.example.blog.model.entity.DistributionPointModel
import com.example.blog.model.entity.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class PostDistributionPointTest {


    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addDistributionPoints(
            Dummies.makeDummyDistributionPoints(500)
        )
    }

    @Test
    fun testBasic() {
        val inserted = DistributionPointModel(
            id = null,
            name = "공덕역 5번출구 앞",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        val response = controller.postDistributionPoint(
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
            InMemoryDb.getLastDistributionPointId(),
            response.id
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIdIsNotNull() {
        val inserted = DistributionPointModel(
            id = 2,
            name = "공덕역 5번출구 앞",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.postDistributionPoint(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNameIsNull() {
        val inserted = DistributionPointModel(
            id = null,
            name = null,
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.postDistributionPoint(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testLocationIsNotNull() {
        val inserted = DistributionPointModel(
            id = null,
            name = "공덕역 5번출구 앞",
            location = null,
            images = emptyArray(),
            feature = emptyArray()
        )
        controller.postDistributionPoint(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testImagesIsNotNull() {
        val inserted = DistributionPointModel(
            id = 2,
            name = "공덕역 5번출구 앞",
            location = Location(20.0, 20.0),
            images = null,
            feature = emptyArray()
        )
        controller.postDistributionPoint(
            inserted
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testFeatureIsNotNull() {
        val inserted = DistributionPointModel(
            id = 2,
            name = "공덕역 5번출구 앞",
            location = Location(20.0, 20.0),
            images = emptyArray(),
            feature = null
        )
        controller.postDistributionPoint(
            inserted
        )
    }
}