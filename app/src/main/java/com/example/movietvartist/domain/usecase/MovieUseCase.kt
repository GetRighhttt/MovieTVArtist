package com.example.movietvartist.domain.usecase

import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.domain.repository.MovieRepository

/**
 * UseCase classes are apart of clean code architecture for modern real-world
 * applications.
 *
 * UseCases are apart of the domain layer.
 *
 * This class holds the responsibility of executing the getMovies() method
 * which returns all movies.
 *
 * However, use case classes cannot do so without the help of a repository layer
 * per each api(movie, shows, actors).
 *
 * We will inject an instance of repository as a constructor parameter into this class to
 * demonstrate dependency injection with Dagger2.
 *
 * Our UseCase layer gets used by our Presentation Layer(ViewModel and Views).
 */
class MovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}