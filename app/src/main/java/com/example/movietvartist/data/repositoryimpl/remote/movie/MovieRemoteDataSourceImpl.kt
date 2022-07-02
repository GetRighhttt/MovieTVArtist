package com.example.movietvartist.data.repositoryimpl.remote.movie

import com.example.movietvartist.data.api.APIService
import com.example.movietvartist.data.model.movie.MovieList
import com.example.movietvartist.data.repositoryimpl.remote.movie.MovieRemoteDataSource
import retrofit2.Response

/**
 * We are injecting our apiService as a dependency.
 *
 * Here we write code to invoke the getMovies() function of the service.
 *
 * Our list requires an apikey, and we're passing in our service as a parameter.
 */

class MovieRemoteDataSourceImpl(
    private val apiService: APIService,
    private val apiKey: String
    ) : MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> = apiService.getMovies(apiKey)
}