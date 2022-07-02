package com.example.movietvartist.data.model.movie
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * To use ROOM database, we have to make the classes that we are going
 * to receive our data from, not the list, as @Entity.
 *
 * Usually for Room database without an API, we would create the table ourselves.
 *
 * @Entity marks it as a table.
 *
 * @ColumnInfo allows us to create columns and change their name.
 *
 * @SerializedName allows us to use the name of our choice for the properties.
 */

@Entity(tableName = "popular_movies")
data class Movie(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "posterPath")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "releaseDate")
    @SerializedName("release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?

)