package com.example.movietvartist.domain.updateusecase

import com.example.movietvartist.data.model.actors.Actor
import com.example.movietvartist.domain.repository.ActorRepository


/**
 * UseCase classes are apart of clean code architecture for modern real-world
 * applications.
 *
 * UseCases are apart of the domain layer.
 *
 * This class holds the responsibility of updating the movies list using updateActors()
 * method from the repository.
 *
 * However, use case classes cannot do so without the help of a repository layer
 * per each api(movie, shows, actors).
 *
 * We will inject an instance of repository as a constructor parameter into this class to
 * demonstrate dependency injection with Dagger2.
 */
class UpdateActorUseCase(private val actorRepository: ActorRepository) {
    suspend fun execute(): List<Actor>? = actorRepository.updateActors()
}