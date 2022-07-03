package com.example.movietvartist.presentation.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movietvartist.domain.updateusecase.UpdateActorUseCase
import com.example.movietvartist.domain.usecase.ActorUseCase
import com.example.movietvartist.domain.usecase.TvShowUseCase


/**
 * Now we will create a get() function to invoke the execute function we made in our
 * use cases.
 *
 * Because we're using Coroutines, we are using a live data block to initiate the method
 *
 * Coroutine will use the main thread since we used IO sources in our Data packages.
 */

class ActorViewModel(
    private val getActorsUseCase: ActorUseCase,
    private val updateActorsUseCase: UpdateActorUseCase
) : ViewModel() {

    /**
     * Now we will create a get() function to invoke the execute function we made in our
     * use cases.
     *
     * Because we're using Coroutines, we are using a live data block to initiate the method
     *
     * Coroutine will use the main thread since we used IO sources in our Data packages.
     *
     * Because we have use case classes now, we no longer have to pass in an instance of our
     * repository to our view model, and we also do not need to create the business logic in this
     * class anymore. It's all done in other parts.
     * This is what clean code architecture is about.
     *
     */

    fun getActors() = liveData {
        val actorList = getActorsUseCase.execute()
        emit(actorList)
    }


    /**
     * Next we will create update method in a similar way using methods we outlined in the
     * use case that we have already created.
     */

    fun updateActors() = liveData {
        val actorList = updateActorsUseCase.execute()
        emit(actorList)
    }
}