package com.example.movietvartist.data.repositoryimpl.remote.tvshow

import com.example.movietvartist.data.api.APIService
import com.example.movietvartist.data.model.tvshow.TvShowList
import com.example.movietvartist.data.repositoryimpl.remote.tvshow.TvShowRemoteDataSource
import retrofit2.Response

/**
 * We are injecting our apiService as a dependency.
 *
 * Here we write code to invoke the getActors() function of the service.
 *
 * Our list requires an apikey, and we're passing in our service as a parameter.
 */

class TvShowRemoteDataSourceImpl(
    private val apiService: APIService,
    private val apiKey: String
    ): TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> = apiService.getTvShows(apiKey)
}