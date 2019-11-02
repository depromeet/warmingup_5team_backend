package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.InMemoryDb
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRelatedSearchWordsTest {


    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addSearchWords(
            Dummies.makeDummySearchWords(500)
        )
    }

    @Test
    fun testBasic() {
        val keyword = "안녕"
        val response = controller.getRelatedSearchWords(keyword).data

        Assert.assertEquals(
            InMemoryDb.getSearchWords().filter {
                it.text.contains(keyword)
            }.size,
            response.filter {
                it.text.contains(keyword)
            }.size
        )
        Assert.assertTrue(
            response.filterNot {
                it.text.contains(keyword)
            }.isEmpty()
        )
    }
}