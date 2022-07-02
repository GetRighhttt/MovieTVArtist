package com.example.movietvartist.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.data.model.tvshow.TvShow

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
interface TvShowDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShow: List<TvShow>)

    /**
     * When working with either deleting all or getting all from a list, we do so using
     * the @Query annotation.
     * @Query essential performs a search.
     */

    @Query("DELETE FROM popular_tv_shows")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM popular_tv_shows")
    suspend fun getAllTvShows(): List<TvShow>
}