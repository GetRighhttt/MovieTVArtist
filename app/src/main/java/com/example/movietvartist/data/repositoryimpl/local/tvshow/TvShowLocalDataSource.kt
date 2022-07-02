package com.example.movietvartist.data.repositoryimpl.local.tvshow

import com.example.movietvartist.data.model.tvshow.TvShow

/**
 * We use public interfaces to communicate between components.
 *
 * Here we need to get a list of type INSTANCES from the database.
 * So that's why we're just returning the data here and not the entire list
 * of all the data.
 */
interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB(): List<TvShow>
    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)
    suspend fun clearALl()
}