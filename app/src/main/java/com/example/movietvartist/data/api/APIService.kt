package com.example.movietvartist.data.api

import com.example.movietvartist.data.model.actors.ActorsList
import com.example.movietvartist.data.model.movie.MovieList
import com.example.movietvartist.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Here we prepare our Retrofit2 service from our APIs.
 *
 * We are using 3 different APIs to return a list of popular movies,
 * TvShows, and actors.
 *
 * We create them as queries because the URL endpoints have "q" followed
 * by our own API key.
 *
 * Every API returns a Response of the list.
 */

interface APIService {

    /**
     * Get request for all popular movies.
     */
    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query(
            "api_key"
        ) apiKey: String
    ): Response<MovieList>

    /**
     * Get requests for all poplar Tv shows.
     */
    @GET("3/tv/popular")
    suspend fun getTvShows(
        @Query(
            "api_key"
        ) apiKey: String
    ): Response<TvShowList>

    /**
     * Get requests for all popular actors.
     */
    @GET("3/person/popular")
    suspend fun getActors(
        @Query(
            "api_key"
        ) apiKey: String
    ): Response<ActorsList>
}