package com.example.movietvartist.data.repositoryimpl

import android.util.Log
import com.example.movietvartist.data.model.actors.Actor
import com.example.movietvartist.data.repositoryimpl.cache.actor.ActorCacheDataSource
import com.example.movietvartist.data.repositoryimpl.local.actor.ActorLocalDataSource
import com.example.movietvartist.data.repositoryimpl.remote.actor.ActorRemoteDataSource
import com.example.movietvartist.domain.repository.ActorRepository

/**
 * This layer is responsible for implementing the methods outlined in our
 * repository layer.
 *
 * When we extend from our repository class, we will be allowed to override the
 * methods we created.
 *
 * get() returns the list of all the list.
 *
 * update() updates the database with the new list.
 *
 * In order to implement those functionalities, we need a remote, local, and cache
 * data source.
 *
 * In clean architecture, we always use public interfaces to communicate
 * between components.
 *
 * So we create interfaces for all 3 data sources.
 */
class ActorRepositoryImpl(
    private val actorRemoteDataSource: ActorRemoteDataSource,
    private val actorLocalDataSource: ActorLocalDataSource,
    private val actorCacheDataSource: ActorCacheDataSource
    ) : ActorRepository {

    override suspend fun getActors(): List<Actor>? {
        return getActorsFromCache()
    }

    override suspend fun updateActors(): List<Actor>? {
        /**
         * In order to update a new list, we have to get data from the api first.
         *
         * Must create reference to a newList, clear the list, then save it to the DB,
         * and to the cache. Then return the list.
         */
        val newListOfActors = getActorsFromApi()
        actorLocalDataSource.clearALl()
        actorLocalDataSource.saveActorsToDB(newListOfActors)
        actorCacheDataSource.saveActorToCache(newListOfActors)
        return newListOfActors
    }

    /**
     * Now we create a method to get the api, database, and cache using those data sources.
     *
     * first, we do so from our API which returns the entire list of movies.
     *
     * We call on our remote data source we created to do so in a try-catch block for error
     * handling.
     */

    suspend fun getActorsFromApi():List<Actor> {
        lateinit var actorList: List<Actor> // create reference to list of movies

        try {
            val response = actorRemoteDataSource.getActors()
            val body = response.body()
            if(body != null) {
                actorList = body.actors
            }
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }
        return actorList
    }

    /**
     * Now we must get data from our database.
     *
     * If there is no data, we must take data from the api and save it to the database.
     */
    suspend fun getActorsFromDB():List<Actor> {
        lateinit var actorList: List<Actor> // create reference to list of movies

        try {
            actorList = actorLocalDataSource.getActorsFromDB()
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }

        if(actorList.size>0) {
            return actorList
        } else {
            actorList = getActorsFromApi()
            actorLocalDataSource.saveActorsToDB(actorList)
        }

        return actorList
    }

    /**
     * Essentially, if cache data is available, system will return them
     *
     * if not, it will take them from the database and save them to the cache.
     */
    suspend fun getActorsFromCache():List<Actor> {
        lateinit var actorList: List<Actor>

        try {
            actorList = actorCacheDataSource.getActorFromCache()
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }
        if(actorList.size>0) {
            return actorList
        } else {
            actorList = getActorsFromDB()
            actorCacheDataSource.saveActorToCache(actorList)
        }
        return actorList
    }
}