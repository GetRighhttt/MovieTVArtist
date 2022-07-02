package com.example.movietvartist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movietvartist.data.database.dao.ActorDao
import com.example.movietvartist.data.database.dao.MovieDao
import com.example.movietvartist.data.database.dao.TvShowDao
import com.example.movietvartist.data.model.actors.Actor
import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.data.model.tvshow.TvShow

/**
 * Here we are going to construct the database we are going to use for our
 * movies, shows, and actors.
 *
 * @TMDBDatabase annotation is what we use to annotate a database.
 *
 * We typically only want to create one instance of a database per app,
 * so we will return a singleton value.
 *
 * Companion object make it easy to return singletons, along with
 * synchronized(this) to ensure one instance gets returned each time.
 *
 * When working with multiple data sources, we state those classes in the entities array
 * parameter.
 */

@Database(entities = [Movie::class, TvShow::class, Actor::class],
version = 2, exportSchema = false)
abstract class TMDBDatabase: RoomDatabase() {

    /**
     * Now we need to define abstract methods to get access to all dao interfaces.
     */
    abstract  fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun actorDao(): ActorDao
}