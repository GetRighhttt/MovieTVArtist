package com.example.movietvartist.data.repositoryimpl.remote.movie

import com.example.movietvartist.data.model.movie.MovieList
import retrofit2.Response

/**
 * We use public interfaces to communicate between components.
 *
 * This interface needs to have a function which returns a list of everything.
 * So that's why the response is a FULL List type and not just the type of data.
 */

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}