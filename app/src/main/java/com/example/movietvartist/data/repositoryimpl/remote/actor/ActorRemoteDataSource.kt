package com.example.movietvartist.data.repositoryimpl.remote.actor

import com.example.movietvartist.data.model.actors.ActorsList
import retrofit2.Response

/**
 * We use public interfaces to communicate between components.
 *
 * This interface needs to have a function which returns a list.
 * So that's why the response is a List type and not just the type of data.
 */
interface ActorRemoteDataSource {
    suspend fun getActors(): Response<ActorsList>
}