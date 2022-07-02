package com.example.movietvartist.data.repositoryimpl

import android.util.Log
import com.example.movietvartist.data.model.tvshow.TvShow
import com.example.movietvartist.data.repositoryimpl.cache.tvshow.TvShowCacheDataSource
import com.example.movietvartist.data.repositoryimpl.local.tvshow.TvShowLocalDataSource
import com.example.movietvartist.data.repositoryimpl.remote.tvshow.TvShowRemoteDataSource
import com.example.movietvartist.domain.repository.TvShowRepository

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
class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
    ) : TvShowRepository {

    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        /**
         * In order to update a new list, we have to get data from the api first.
         *
         * Must create reference to a newList, clear the list, then save it to the DB,
         * and to the cache. Then return the list.
         */
        val newTvShowList = getTvShowsFromApi()
        tvShowLocalDataSource.clearALl()
        tvShowLocalDataSource.saveTvShowsToDB(newTvShowList)
        tvShowCacheDataSource.saveTvShowsToCache(newTvShowList)
        return newTvShowList
    }

    /**
     * Now we create a method to get the api, database, and cache using those data sources.
     *
     * first, we do so from our API which returns the entire list of movies.
     *
     * We call on our remote data source we created to do so in a try-catch block for error
     * handling.
     */

    suspend fun getTvShowsFromApi():List<TvShow> {
        lateinit var tvShowList: List<TvShow> // create reference to list of movies

        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if(body != null) {
                tvShowList = body.tvShows
            }
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }
        return tvShowList
    }

    /**
     * Now we must get data from our database.
     *
     * If there is no data, we must take data from the api and save it to the database.
     */
    suspend fun getTvShowsFromDB():List<TvShow> {
        lateinit var tvShowList: List<TvShow> // create reference to list of movies

        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }

        if(tvShowList.size>0) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromApi()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
        }

        return tvShowList
    }

    /**
     * Essentially, if cache data is available, system will return them
     *
     * if not, it will take them from the database and save them to the cache.
     */
    suspend fun getTvShowsFromCache():List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())
        }
        if(tvShowList.size>0) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }
}