package com.example.blog.api

import com.example.blog.Dummies
import com.example.blog.controller.HomeController
import com.example.blog.db.InMemoryDb
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRecentlySearchWordsTest {


    private val controller = HomeController()

    @Before
    fun before() {
        InMemoryDb.addSearchWords(
            Dummies.makeDummySearchWords(500)
        )
    }

    @Test
    fun testBasic() {
        val response = controller.getRecentlySearchWords(20).data

        Assert.assertEquals(
            20, response.size
        )
        Assert.assertEquals(
            InMemoryDb.getSearchWords().takeLast(20).toTypedArray(), response
        )
    }
}