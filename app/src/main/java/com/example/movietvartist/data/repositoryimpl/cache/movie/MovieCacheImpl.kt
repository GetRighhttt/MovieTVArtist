package com.example.movietvartist.data.repositoryimpl.cache.movie

import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.data.repositoryimpl.cache.movie.MovieCacheDataSource

/**
 * In this class, we will create an array of instances, and when the app is launched for
 * the first time, we will assign that list to this arrayList.
 *
 * We will keep this as a singleton using dagger.
 *
 * This basically serves as a way the user can load the list again without going
 * to the database. It saves a cache copy of itself.
 */
class MovieCacheImpl: MovieCacheDataSource {

    private var movieList = ArrayList<Movie>()
    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}