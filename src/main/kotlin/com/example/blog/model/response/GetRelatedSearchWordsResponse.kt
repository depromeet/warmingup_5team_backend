package com.example.blog.model.response

import com.example.blog.model.entity.SearchWord

data class GetRelatedSearchWordsResponse(
    val data: Array<SearchWord>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetRelatedSearchWordsResponse

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}