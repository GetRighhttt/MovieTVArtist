package com.example.movietvartist.data.repositoryimpl.remote.actor

import com.example.movietvartist.data.api.APIService
import com.example.movietvartist.data.model.actors.ActorsList
import retrofit2.Response


/**
 * We are injecting our apiService as a dependency.
 *
 * Here we write code to invoke the getActors() function of the service.
 *
 * Our list requires an apikey, and we're passing in our service as a parameter.
 */
class ActorRemoteDataSourceImpl(
    private val apiService: APIService,
    private val apiKey: String
    ) : ActorRemoteDataSource {
    override suspend fun getActors(): Response<ActorsList> = apiService.getActors(apiKey)
}