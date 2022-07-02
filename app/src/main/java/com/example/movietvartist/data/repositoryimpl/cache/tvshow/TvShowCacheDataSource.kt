package com.example.movietvartist.data.repositoryimpl.cache.tvshow

import com.example.movietvartist.data.model.tvshow.TvShow

/**
 * We use public interfaces to communicate between components.
 *
 * Here we need to define two functions that save to cache and receive from cache.
 */
interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache():List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)
}