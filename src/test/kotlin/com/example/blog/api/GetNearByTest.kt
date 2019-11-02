package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.DummyDb
import com.example.blog.db.InMemoryDb
import com.example.blog.model.entity.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetNearByTest {

    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addCats(
            Dummies.makeDummyCats(200)
        )
        InMemoryDb.addDistributionPoints(
            Dummies.makeDummyDistributionPoints(200)
        )
        DummyDb.addHospitals(
            Dummies.makeDummyHospitals(200)
        )
        DummyDb.addShelters(
            Dummies.makeDummyShelters(200)
        )
    }

    @Test
    fun testBasic() {
        val testLocation = Location(200.0, 400.0)

        val response = controller.getNearBy(
            testLocation.x,
            testLocation.y
        ).data

        Assert.assertTrue(
            response.cats.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            } && response.distributionPoints.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            } && response.hospital.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            } && response.shelters.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            }
        )
    }

    @Test
    fun testLocationMax() {
        val testLocation = Location(Double.MAX_VALUE, Double.MIN_VALUE)

        val response = controller.getNearBy(
            testLocation.x,
            testLocation.y
        ).data

        Assert.assertTrue(
            response.cats.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            } && response.distributionPoints.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            } && response.hospital.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            } && response.shelters.none {
                Math.abs(it.location.x - testLocation.x) > HomeController.RANGE_OF_NEAR_BY
                    && Math.abs(it.location.y - testLocation.y) > HomeController.RANGE_OF_NEAR_BY
            }
        )
    }
}