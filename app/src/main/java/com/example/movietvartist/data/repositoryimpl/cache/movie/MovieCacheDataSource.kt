package com.example.movietvartist.data.repositoryimpl.cache.movie

import com.example.movietvartist.data.model.movie.Movie

/**
 * We use public interfaces to communicate between components.
 *
 * Here we need to define two functions that save to cache and receive from cache.
 */
interface MovieCacheDataSource {
    suspend fun getMoviesFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies: List<Movie>)
}