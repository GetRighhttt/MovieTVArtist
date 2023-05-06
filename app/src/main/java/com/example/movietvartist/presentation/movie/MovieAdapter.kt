package com.example.movietvartist.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movietvartist.R
import com.example.movietvartist.data.model.movie.Movie
import com.example.movietvartist.databinding.ListItemBinding
import java.util.concurrent.Flow

/**
 * Adapter for the movie.
 *
 * DataBinding should be the type of the list item.
 *
 * Extend ViewHolder class for the inner class, and Adapter for outer.
 */

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    /**
     * Must first define an array list of your data type.
     * Then create a method to set that list.
     */
    private val movieList = ArrayList<Movie>()
    fun setList(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    /**
     * Separate class that we use to create our ViewHolder.
     *
     * Must pass in our DataBinding variable in our constructor.
     *
     * This classes main purpose is to allow our implemented methods to call
     * on it for their necessary functionality.
     *
     * I.E. fun bind() called by onBindViewHolder to bind its views.
     */

    class MovieViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                titleTextView.text = movie.title
                descriptionTextView.text = movie.overview
                releaseDateTextView.text = movie.releaseDate

                /**
                 * according to the documentation, we have to add the source in order
                 * to display the image.
                 *
                 * Then we use Glide to help display the image from the URL.
                 */
                val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
                Glide.with(binding.imageView.context)
                    .load(posterURL)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageView)
            }
        }

        /**
         * Get our linear layout ID so I can use it for animation in onBindViewHolder()
         */
        val linearListItem = binding.linearListItem

        /**
         * companion object used to store a method needed for the onCreateViewHolder to
         * inflate the list item layout.
         *
         * We could do this in the onCreateViewHolder but it's not recommended.
         * Best practice for Separation of Concerns.
         */
        companion object {
            fun inflateFrom(parent: ViewGroup): MovieViewHolder {
                // get an instance of layout inflater
                val layoutInflater = LayoutInflater.from(parent.context)
                // need data binding object of list_item layout
                val binding: ListItemBinding = DataBindingUtil.inflate(
                    layoutInflater, R.layout.list_item, parent, false
                )
                return MovieViewHolder(binding)
            }
        }
    }

    /**
     * This is where we inflate the list item using our companion object from our
     * inflateFrom method we created in the ViewHolder class.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MovieViewHolder = MovieViewHolder.inflateFrom(parent)

    /**
     * Used to display data on the list item; bind data to its position to be
     * displayed.
     *
     * Position represents the count value of each list_item.
     *
     * Here is where we can attach animations to our items as they are.
     */

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.linearListItem.startAnimation(
            AnimationUtils
                .loadAnimation(holder.itemView.context, R.anim.favorite_anim)
        )
    }

    /**
     * Typically used just to return the size of the entire list.
     *
     * RecyclerView library will create separate rows based on the count.
     */

    override fun getItemCount(): Int = movieList.size

}