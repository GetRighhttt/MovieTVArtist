package com.example.movietvartist.data.model.actors


import com.example.movietvartist.data.model.actors.Actor
import com.google.gson.annotations.SerializedName

data class ActorsList(

    @SerializedName("results")
    val actors: List<Actor>
)