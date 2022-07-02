package com.example.movietvartist.data.repositoryimpl.cache.tvshow

import com.example.movietvartist.data.model.tvshow.TvShow
import com.example.movietvartist.data.repositoryimpl.cache.tvshow.TvShowCacheDataSource

/**
 * In this class, we will create an array of instances, and when the app is launched for
 * the first time, we will assign that list to this arrayList.
 *
 * We will keep this as a singleton using dagger.
 *
 * This basically serves as a way the user can load the list again without going
 * to the database, and saves a copy of itself.
 */
class TvShowCacheImpl : TvShowCacheDataSource {
    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getTvShowsFromCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }
}