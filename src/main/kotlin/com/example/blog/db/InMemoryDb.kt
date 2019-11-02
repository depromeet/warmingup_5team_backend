package com.example.blog.db

import com.example.blog.model.entity.Cat
import com.example.blog.model.entity.DistributionPoint
import com.example.blog.model.entity.SearchWord

object InMemoryDb {

    private val cats: HashSet<Cat> = hashSetOf()
    private val distributionPoints: HashSet<DistributionPoint> = hashSetOf()
    private val searchWords: HashSet<SearchWord> = hashSetOf()

    private var lastCatId = 0
    private var lastDistributionPointId = 0


    fun getCats(): Array<Cat> = cats.toTypedArray()
    fun getDistributionPoints(): Array<DistributionPoint> = distributionPoints.toTypedArray()
    fun getSearchWords(): Array<SearchWord> = searchWords.toTypedArray()

    fun addCats(item: Array<Cat>): Array<Cat> = cats.run {
        addAll(item)
        lastCatId += item.size
        toTypedArray()
    }

    fun addDistributionPoints(item: Array<DistributionPoint>): Array<DistributionPoint> = distributionPoints.run {
        addAll(item)
        lastDistributionPointId += item.size
        toTypedArray()
    }

    fun addSearchWords(item: Array<SearchWord>): Array<SearchWord> = searchWords.run {
        addAll(item)
        toTypedArray()
    }

    fun getLastCatId(): Int = lastCatId

    fun getLastDistributionPointId(): Int = lastDistributionPointId
}