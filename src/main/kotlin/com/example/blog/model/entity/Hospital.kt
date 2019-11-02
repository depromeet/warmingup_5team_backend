package com.example.blog.model.entity

data class Hospital(
    val name: String,
    val location: Location,
    val openTime: String,
    val closeTime: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hospital

        if (name != other.name) return false
        if (location != other.location) return false
        if (openTime != other.openTime) return false
        if (closeTime != other.closeTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + openTime.hashCode()
        result = 31 * result + closeTime.hashCode()
        return result
    }
}