package com.example.movietvartist.data.repositoryimpl.local.movie

import com.example.movietvartist.data.database.dao.MovieDao
import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.data.repositoryimpl.local.movie.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Here is where we define the member functions outline in our Local interface.
 */

class MovieLocalDataSourceImpl(private val movieDao: MovieDao)
    : MovieLocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearALl() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}