package com.example.grupilem.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieDetail(
    var title: String = "",
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    @SerializedName("backdrop_path")
    var backDropPath: String = "",
    var overview: String = "",
    @SerializedName("release_date")
    var releaseData: String = "",
    @SerializedName("vote_count")
    var voteCount: Int = 0,
    var status: String = "",
    var homepage: String = ""
)
