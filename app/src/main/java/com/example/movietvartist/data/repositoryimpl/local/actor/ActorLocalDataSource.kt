package com.example.movietvartist.data.repositoryimpl.local.actor

import com.example.movietvartist.data.model.actors.Actor

/**
 * We use public interfaces to communicate between components.
 *
 * Here we need to get a list of type INSTANCES from the database.
 * So that's why we're just returning the data here and not the entire list
 * of all the data.
 */
interface ActorLocalDataSource {
    suspend fun getActorsFromDB(): List<Actor>
    suspend fun saveActorsToDB(actors: List<Actor>)
    suspend fun clearALl()
}