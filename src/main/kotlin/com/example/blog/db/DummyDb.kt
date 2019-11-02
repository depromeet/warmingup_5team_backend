package com.example.blog.db

import com.example.blog.model.entity.Hospital
import com.example.blog.model.entity.Shelter

object DummyDb {

    private val hospitals: HashSet<Hospital> = hashSetOf()
    private val shelters: HashSet<Shelter> = hashSetOf()

    private var lastHospitalId = 0
    private var lastShelterId = 0

    fun addHospitals(item: Array<Hospital>): Array<Hospital> = hospitals.run {
        addAll(item)
        lastHospitalId += item.size
        toTypedArray()
    }

    fun addShelters(item: Array<Shelter>): Array<Shelter> = shelters.run {
        addAll(item)
        lastShelterId = item.size
        toTypedArray()
    }

    fun getHospital(): Array<Hospital> = hospitals.toTypedArray()

    fun getShelter(): Array<Shelter> = shelters.toTypedArray()
}