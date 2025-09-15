package com.example.final_assignment.model.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
data class DashboardResponse(
    val entities: List<Book>,
    val entityTotal: Int
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Book(
    val title: String,
    val author: String,
    val genre: String,
    val publicationYear: Int,
    val description: String
) : Parcelable
