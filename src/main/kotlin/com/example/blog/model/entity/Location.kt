package com.example.blog.model.entity

data class Location(
    val x: Double,
    val y: Double
) {

    fun isNearBy(range: Double, location: Location) =
        range >= Math.abs(x - location.x) && range >= Math.abs(y - location.y)
}
