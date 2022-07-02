package com.example.movietvartist.domain.updateusecase

import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.domain.repository.MovieRepository

/**
 * UseCase classes are apart of clean code architecture for modern real-world
 * applications.
 *
 * UseCases are apart of the domain layer.
 *
 * This class holds the responsibility of updating the movies list using updateMovies()
 * method from the repository.
 *
 * However, use case classes cannot do so without the help of a repository layer
 * per each api(movie, shows, actors).
 *
 * We will inject an instance of repository as a constructor parameter into this class to
 * demonstrate dependency injection with Dagger2.
 */

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {

    /**
     * Method to update movie list with updateMovie() method from our repository.
     */
    suspend fun execute():List<Movie>? = movieRepository.updateMovies()
}