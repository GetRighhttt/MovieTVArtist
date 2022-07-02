package com.example.movietvartist.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietvartist.R
import com.example.movietvartist.databinding.ActivityMovieBinding
import com.example.movietvartist.presentation.di.core.data.Injector
import javax.inject.Inject

/**
 * Movie Activity.
 */
class MovieActivity : AppCompatActivity() {
    /**
     * Injecting into our Activity
     */
    @Inject
    lateinit var factory: MovieViewModelFactory

    /**
     * Declaring reference variables.
     */
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        /**
         * Allowing ourselves access to inject into this activity.
         */
        (application as Injector).createMovieSubComponent()
            .inject(this)

        /**
         * Now we're using our ViewModelProvider to get an instance of MovieViewModel.
         */
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        initRecyclerView()
    }

    /**
     * Method to initialize the recycler view.
     *
     * bind the layout manager;
     *
     * set the adapter, then set it to recycler view.
     */
    private fun initRecyclerView() {
        binding.apply {
            movieRecyclerView.layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = MovieAdapter()
            movieRecyclerView.adapter = adapter
        }
        displayPopularMovies()
    }

    /**
     * Get movies; Observe Live Data changes, and display them.
     *
     * Check if null, if not, set the list and notify data has changed using adapter method.
     */
    private fun displayPopularMovies() {

        binding.apply {
            movieProgressBar.visibility = View.VISIBLE
            val responseLiveData = movieViewModel.getMovies()
            responseLiveData.observe(this@MovieActivity, Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.movieProgressBar.visibility = View.GONE
                } else {
                    movieProgressBar.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "No Data available.", Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

}