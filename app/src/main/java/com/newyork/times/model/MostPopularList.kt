package com.newyork.times.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MostPopularList(
    val status: String,
    @Json(name = "num_results") val numResults: Int,
    val results: List<Article>
) : Parcelable
