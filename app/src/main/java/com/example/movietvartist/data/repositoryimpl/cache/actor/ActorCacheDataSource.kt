package com.example.movietvartist.data.repositoryimpl.cache.actor

import com.example.movietvartist.data.model.actors.Actor

/**
 * We use public interfaces to communicate between components.
 *
 * Here we need to define two functions that save to cache and receive from cache.
 */
interface ActorCacheDataSource {
    suspend fun getActorFromCache():List<Actor>
    suspend fun saveActorToCache(actors: List<Actor>)
}