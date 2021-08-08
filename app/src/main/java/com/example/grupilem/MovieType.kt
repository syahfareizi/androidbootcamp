package com.example.grupilem

enum class MovieType(val value: String, val id: Int) {
    NOW_PLAYING("Now Playing", 1),
    UPCOMING("Upcoming", 2),
    LATEST("Latest", 3),
    POPULAR("Popular", 4),
    TOP_RATED("Top Rated", 5)
}