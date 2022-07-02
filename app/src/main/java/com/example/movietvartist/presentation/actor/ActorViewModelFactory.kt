package com.example.movietvartist.presentation.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietvartist.domain.updateusecase.UpdateActorUseCase
import com.example.movietvartist.domain.usecase.ActorUseCase

/**
 * Because our view model has parameters, we need a factory class to
 * send our UI an instance of view model.
 *
 * Parameters should be the same from the ViewModel.
 */

class ActorViewModelFactory(
    private val getActorsUseCase: ActorUseCase,
    private val updateActorsUseCase: UpdateActorUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActorViewModel(getActorsUseCase, updateActorsUseCase) as T
    }
}