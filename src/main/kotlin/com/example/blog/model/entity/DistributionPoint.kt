package com.example.blog.model.entity

import java.time.ZonedDateTime

open class DistributionPointModel(
    open val id: Int?,
    open val name: String?,
    open val location: Location?,
    open val images: Array<Image>?,
    open val feature: Array<String>?
)

data class DistributionPoint(
    override val id: Int,
    override val name: String,
    override val location: Location,
    override val images: Array<Image>,
    override val feature: Array<String>,
    val createdAt: ZonedDateTime
) : DistributionPointModel(id, name, location, images, feature) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DistributionPoint

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int = id
}