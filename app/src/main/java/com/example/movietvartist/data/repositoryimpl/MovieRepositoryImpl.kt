package com.example.movietvartist.data.repositoryimpl

import android.util.Log
import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.data.repositoryimpl.cache.movie.MovieCacheDataSource
import com.example.movietvartist.data.repositoryimpl.local.movie.MovieLocalDataSource
import com.example.movietvartist.data.repositoryimpl.remote.movie.MovieRemoteDataSource
import com.example.movietvartist.domain.repository.MovieRepository

/**
 * This layer is responsible for implementing the methods outlined in our
 * repository layer.
 *
 *
 * get() returns the list of all the list.
 *
 * update() updates the database with the new list.
 *
 * In order to implement those functionalities, we need a remote, local, and cache
 * data source.
 *
 * In clean architecture, we always use public interfaces to communicate
 * between components.
 *
 * So we create interfaces for all 3 data sources, and use them as parameters.
 */
class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {

    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        /**
         * In order to update a new list, we have to get data from the api first.
         *
         * Must create reference to a newList, clear the list, then save it to the DB,
         * and to the cache. Then return the list.
         */
        val newListOfMovies = getMoviesFromApi()
        movieLocalDataSource.clearALl()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    /**
     * Now we create a method to get the api, database, and cache using those data sources.
     *
     * first, we do so from our API which returns the entire list of movies.
     *
     * We call on our remote data source we created to do so in a try-catch block for error
     * handling.
     */

    suspend fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if(body!=null){
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

    /**
     * Now we must get movies from our database.
     *
     * If there is no data, we must take data from the api and save it to the database.
     */
    suspend fun getMoviesFromDB():List<Movie> {
        lateinit var movieList: List<Movie>// create reference to list of movies

        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
            } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }

        if(movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromApi()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }

        return movieList
    }

    /**
     * Essentially, if cache data is available, system will return them
     *
     * if not, it will take them from the database and save them to the cache.
     */
    suspend fun getMoviesFromCache():List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
            } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }

        if(movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }

        return movieList
    }
}