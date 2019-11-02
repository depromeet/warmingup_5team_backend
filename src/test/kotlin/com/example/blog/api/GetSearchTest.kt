package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.DummyDb
import com.example.blog.db.InMemoryDb
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetSearchTest {


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
        val keyword = "안녕"
        val response = controller.getSearch(keyword).data

        Assert.assertEquals(
            InMemoryDb.getCats().filter {
                it.name.contains(keyword)
            }.size,
            response.cats.filter {
                it.name.contains(keyword)
            }.size
        )
        Assert.assertTrue(
            response.cats.filterNot {
                it.name.contains(keyword)
            }.isEmpty()
        )

        Assert.assertEquals(
            InMemoryDb.getDistributionPoints().filter {
                it.name.contains(keyword)
            }.size,
            response.distributionPoints.filter {
                it.name.contains(keyword)
            }.size
        )
        Assert.assertTrue(
            response.cats.filterNot {
                it.name.contains(keyword)
            }.isEmpty()
        )

        Assert.assertEquals(
            DummyDb.getHospital().filter {
                it.name.contains(keyword)
            }.size,
            response.cats.filter {
                it.name.contains(keyword)
            }.size
        )
        Assert.assertTrue(
            response.hospital.filterNot {
                it.name.contains(keyword)
            }.isEmpty()
        )

        Assert.assertEquals(
            DummyDb.getShelter().filter {
                it.name.contains(keyword)
            }.size,
            response.cats.filter {
                it.name.contains(keyword)
            }.size
        )
        Assert.assertTrue(
            response.shelters.filterNot {
                it.name.contains(keyword)
            }.isEmpty()
        )
    }
}