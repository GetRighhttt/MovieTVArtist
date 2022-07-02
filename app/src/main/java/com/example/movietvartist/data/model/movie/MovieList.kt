package com.example.movietvartist.data.model.movie

import com.example.movietvartist.data.model.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("results")
    val movies: List<Movie>
)
