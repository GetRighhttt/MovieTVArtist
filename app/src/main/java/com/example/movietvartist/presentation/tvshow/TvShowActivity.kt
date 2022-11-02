package com.example.movietvartist.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietvartist.R
import com.example.movietvartist.databinding.ActivityTvShowBinding
import com.example.movietvartist.presentation.di.core.data.Injector
import javax.inject.Inject

/**
 * TvShow Activity.
 */
class TvShowActivity : AppCompatActivity() {

    /**
     * Injecting into our Activity
     */
    @Inject
    lateinit var factory: TvShowViewModelFactory

    /**
     * Declaring reference variables.
     */
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        /*
  Hide action bar and theme(OPTIONAL).
   */
//        supportActionBar?.hide()
//        this.window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        /**
         * Allowing ourselves access to inject into this activity.
         */
        (application as Injector).createTvShowSubComponent()
            .inject(this)

        /**
         * Now we're using our ViewModelProvider to get an instance of ViewModel.
         */
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        initRecyclerView()
    }

    /**
     * Method to initialize the recycler view.
     *
     * bind the layout manager;
     *
     * set the adapter, then set it to recycler view to Vertical.
     */
    private fun initRecyclerView() {
        binding.apply {
            tvRecyclerView.layoutManager = LinearLayoutManager(
                this@TvShowActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = TvShowAdapter()
            tvRecyclerView.adapter = adapter
        }
        displayPopularTvShows()
    }

    /**
     * Get movies; Observe Live Data changes, and display them.
     *
     * Check if null, if not, set the list and notify data has changed using adapter method.
     */

    private fun displayPopularTvShows() {
        binding.apply {
            tvProgressBar.visibility = View.VISIBLE
            val responseLiveData = tvShowViewModel.getTvShows()
            responseLiveData.observe(this@TvShowActivity, Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    tvProgressBar.visibility = View.GONE
                } else {
                    tvProgressBar.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "No Data available.", Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    /**
     * In order for us to activate the update button to show in the top right corner
     * of the activity, we have to override onCreateOptionsMenu() and
     * onOptionsItemSelected().
     */


    /*
    Method to inflate the menu item(update button).
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    /*
    Now we will call our update() function and set the value to true when it is
    selected.

    If not selected, return the regular item.(nothing)
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateTvShows()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /*
   Method to actually update the items in the layout with the update button on the menu.

   We're first going to set the visibility of the progress bar and then invoke the
   update movies function from our View Model and get the new list of movies as live
   data.

   Check if null, if not, set the list and notify data has changed using adapter method
    */
    private fun updateTvShows() {
        binding.apply {
            tvProgressBar.visibility = View.VISIBLE
            val response = tvShowViewModel.updateTvShows()
            response.observe(this@TvShowActivity, Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.tvProgressBar.visibility = View.INVISIBLE
                } else {
                    tvProgressBar.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "Update didn't work.", Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}
