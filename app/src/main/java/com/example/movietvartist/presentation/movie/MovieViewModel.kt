package com.example.movietvartist.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movietvartist.databinding.ActivityMovieBinding
import com.example.movietvartist.domain.updateusecase.UpdateMoviesUseCase
import com.example.movietvartist.domain.usecase.MovieUseCase

/**
 * ViewModel to pass in our UseCases.
 *
 * We are passing in our UseCases as parameters, and also an instance of data binding
 * that we declared in our xml file for each activity.
 *
 * This ViewModel class has parameters so therefore we need to create a ViewModel factory
 * class for this.
 */
class MovieViewModel(
    private val getMoviesUseCase: MovieUseCase,
    private val updateMovieUseCase: UpdateMoviesUseCase
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
     */
    fun getMovies() = liveData {
        val movieList = getMoviesUseCase.execute()
        emit(movieList)
    }

    /**
     * Next we will create update method in a similar way using methods we outlined in the
     * use case that we have already created.
     */
    fun updateMovies() = liveData {
        val movieList = updateMovieUseCase.execute()
        emit(movieList)
    }


}