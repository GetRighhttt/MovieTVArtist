package com.example.movietvartist.domain.repository

import com.example.movietvartist.data.model.movie.Movie

/**
 * Another component of the domain layer which we use to define an abstract function
 * to return a list of all the data we need.
 *
 * UseCase classes don't need to know about implementation. That is what we do here.
 *
 * The implementation class of this interface will implement this function.
 *
 * Our repository layer gets used by our data layer.
 */
interface MovieRepository {

    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?

}