package com.example.movietvartist.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietvartist.domain.updateusecase.UpdateMoviesUseCase
import com.example.movietvartist.domain.usecase.MovieUseCase

/**
 * Because our view model has parameters, we need a factory class to
 * send our UI an instance of view model.
 *
 * Parameters should be the same from the ViewModel.
 */
class MovieViewModelFactory(
    private val getMoviesUseCase: MovieUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
    }
}