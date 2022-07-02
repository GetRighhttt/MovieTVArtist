package com.example.movietvartist.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietvartist.domain.updateusecase.UpdateTvShowUseCase
import com.example.movietvartist.domain.usecase.TvShowUseCase


/**
 * Because our view model has parameters, we need a factory class to
 * send our UI an instance of view model.
 *
 * Parameters should be the same from the ViewModel.
 */

class TvShowViewModelFactory(
    private val getTvShowsUseCase: TvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTvShowsUseCase, updateTvShowUseCase) as T
    }
}