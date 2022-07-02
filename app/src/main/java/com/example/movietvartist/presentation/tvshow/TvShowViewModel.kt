package com.example.movietvartist.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movietvartist.domain.updateusecase.UpdateTvShowUseCase
import com.example.movietvartist.domain.usecase.TvShowUseCase

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
 */

class TvShowViewModel(
    private val getTvShowUseCase: TvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModel() {

    /**
     * Now we will create a get() function to invoke the execute function we made in our
     * use cases.
     *
     * Because we're using Coroutines, we are using a live data block to initiate the method
     *
     * Coroutine will use the main thread since we used IO sources in our Data packages.
     */

    fun getTvShows() = liveData {
        val tvShowList = getTvShowUseCase.execute()
        emit(tvShowList)
    }

    /**
     * Next we will create update method in a similar way using methods we outlined in the
     * use case that we have already created.
     */

    fun updateTvShows() = liveData {
        val tvShowList = updateTvShowUseCase.execute()
        emit(tvShowList)
    }
}