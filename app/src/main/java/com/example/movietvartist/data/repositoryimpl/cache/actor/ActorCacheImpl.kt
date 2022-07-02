package com.example.movietvartist.data.repositoryimpl.cache.actor

import com.example.movietvartist.data.model.actors.Actor

/**
 * In this class, we will create an array of instances, and when the app is launched for
 * the first time, we will assign that list to this arrayList.
 *
 * We will keep this as a singleton using dagger.
 *
 * This basically serves as a way the user can load the list again without going
 * to the database, and saves a copy of itself.
 */
class ActorCacheImpl : ActorCacheDataSource {
    private var actorList = ArrayList<Actor>()

    override suspend fun getActorFromCache(): List<Actor> {
        return actorList
    }

    override suspend fun saveActorToCache(actors: List<Actor>) {
        actorList.clear()
        actorList = ArrayList(actors)
    }
}