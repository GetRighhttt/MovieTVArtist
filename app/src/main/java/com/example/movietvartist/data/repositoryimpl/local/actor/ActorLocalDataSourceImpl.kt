package com.example.movietvartist.data.repositoryimpl.local.actor

import com.example.movietvartist.data.database.dao.ActorDao
import com.example.movietvartist.data.model.actors.Actor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Here is where we define the member functions outline in our Local interface.
 */

class ActorLocalDataSourceImpl(private val actorDao: ActorDao)
    : ActorLocalDataSource {

    override suspend fun getActorsFromDB(): List<Actor> {
        return actorDao.getAllActors()
    }

    override suspend fun saveActorsToDB(actors: List<Actor>) {
        CoroutineScope(Dispatchers.IO).launch {
            actorDao.saveActors(actors)
        }
    }

    override suspend fun clearALl() {
        CoroutineScope(Dispatchers.IO).launch {
            actorDao.deleteAllActors()
        }
    }
}