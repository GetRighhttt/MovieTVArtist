package com.example.movietvartist.data.repositoryimpl.local.movie

import com.example.movietvartist.data.model.movie.Movie

/**
 * We use public interfaces to communicate between components.
 *
 * Here we need to get a list of type INSTANCES from the database.
 * So that's why we're just returning the data here and not the entire list
 * of all the data.
 */
interface MovieLocalDataSource {
    suspend fun getMoviesFromDB(): List<Movie>
    suspend fun saveMoviesToDB(movies: List<Movie>)
    suspend fun clearALl()
}