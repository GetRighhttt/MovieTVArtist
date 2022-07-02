package com.example.movietvartist.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movietvartist.data.model.actors.Actor
import com.example.movietvartist.data.model.movie.Movie

/**
 * We need a Data Access Object to perform operations for our database.
 *
 * @Dao = data access object annotation required for each interface we create.
 *
 * Insert, Delete, Save, Update, or whatever operations for our database will be
 * defined in this layer, and then used for outside access from here by calling
 * an instance of dao we create in other parts.
 */

@Dao
interface ActorDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActors(actors: List<Actor>)

    /**
     * When working with either deleting all or getting all from a list, we do so using
     * the @Query annotation.
     * @Query essential performs a search.
     */

    @Query("DELETE FROM popular_actors")
    suspend fun deleteAllActors()

    @Query("SELECT * FROM popular_actors")
    suspend fun getAllActors():List<Actor>
}