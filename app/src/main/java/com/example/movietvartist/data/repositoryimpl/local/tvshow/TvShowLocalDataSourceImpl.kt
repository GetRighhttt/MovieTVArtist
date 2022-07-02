package com.example.movietvartist.data.repositoryimpl.local.tvshow

import com.example.movietvartist.data.database.dao.TvShowDao
import com.example.movietvartist.data.model.tvshow.TvShow
import com.example.movietvartist.data.repositoryimpl.local.tvshow.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Here is where we define the member functions outline in our Local interface.
 */

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao)
    : TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getAllTvShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearALl() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}