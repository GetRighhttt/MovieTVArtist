package com.example.movietvartist.domain.repository

import com.example.movietvartist.data.model.tvshow.TvShow

/**
 * Another component of the domain layer which we use to define an abstract function
 * to return a list of all the data we need.
 *
 * UseCase classes don't need to know about implementation. That is what we do here.
 *
 * The implementation class of this interface will implement this function.
 *
 * Our repository layer gets used by our data layer.
 */
interface TvShowRepository {

    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShows(): List<TvShow>?

}