package com.example.movietvartist.presentation.di.core.data

import android.content.Context
import androidx.room.Room
import com.example.movietvartist.data.database.TMDBDatabase
import com.example.movietvartist.data.database.dao.ActorDao
import com.example.movietvartist.data.database.dao.MovieDao
import com.example.movietvartist.data.database.dao.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Now we are using dagger for dependency injection with our database.
 */
@Module
class DatabaseModule {
    /**
     * Method to provide our dependencies to our database layer of our app.
     *
     * And of course we want to provide single instances every time.
     */

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(
            context,
            TMDBDatabase::class.java, "Webclient"
        )
            .build()
    }

    /**
     * We have 3 DAO INTERFACES in this project and our local data source needs them as
     * dependencies.
     *
     * So we will create methods to provide them with them now.
     *
     * And our database will be passed in as constructor to each one.
     *
     * And we will return an instance of each one.
     */

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDao {
        return tmdbDatabase.tvShowDao()
    }

    @Singleton
    @Provides
    fun provideActorDao(tmdbDatabase: TMDBDatabase): ActorDao {
        return tmdbDatabase.actorDao()
    }
}