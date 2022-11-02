package com.example.movietvartist.presentation.actor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietvartist.R
import com.example.movietvartist.databinding.ActivityActorBinding
import com.example.movietvartist.presentation.di.core.data.Injector

import javax.inject.Inject

/**
 * Actor activity.
 */
class ActorActivity : AppCompatActivity() {

    /**
     * Injecting into our Activity
     */
    @Inject
    lateinit var factory: ActorViewModelFactory

    /**
     * Declaring reference variables.
     */
    private lateinit var binding: ActivityActorBinding
    private lateinit var actorViewModel: ActorViewModel
    private lateinit var adapter: ActorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_actor)

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
        (application as Injector).createActorSubComponent()
            .inject(this)

        /**
         * Now we're using our ViewModelProvider to get an instance of ViewModel.
         */
        actorViewModel = ViewModelProvider(this, factory)[ActorViewModel::class.java]
        initRecyclerView()

    }

    /**
     * Method to initialize the recycler view.
     *
     * bind the layout manager;
     *
     * set the adapter, then set it to recycler view to Horizontal.
     */
    private fun initRecyclerView() {
        binding.apply {
            actorRecyclerView.layoutManager = LinearLayoutManager(this@ActorActivity,
                LinearLayoutManager.HORIZONTAL, false)
            adapter = ActorAdapter()
            actorRecyclerView.adapter = adapter
        }
        displayPopularActors()
    }
    /**
     * Get movies; Observe Live Data changes, and display them.
     *
     * Check if null, if not, set the list and notify data has changed using adapter method.
     */

    private fun displayPopularActors() {
        binding.apply {
            actorProgressBar.visibility = View.VISIBLE
            val responseLiveData = actorViewModel.getActors()
            responseLiveData.observe(this@ActorActivity, Observer {
                if(it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    actorProgressBar.visibility = View.GONE
                } else {
                    actorProgressBar.visibility = View.GONE
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
        return when(item.itemId) {
            R.id.action_update -> {
                updateActors()
                true
            } else -> return super.onOptionsItemSelected(item)
        }
    }

    /*
   Method to actually update the items in the layout with the update button on the menu.

   We're first going to set the visibility of the progress bar and then invoke the
   update movies function from our View Model and get the new list of movies as live
   data.

   Check if null, if not, set the list and notify data has changed using adapter method
    */
    private fun updateActors() {
        binding.apply {
            actorProgressBar.visibility = View.VISIBLE
            val response = actorViewModel.updateActors()
            response.observe(this@ActorActivity, Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.actorProgressBar.visibility = View.INVISIBLE
                } else {
                    actorProgressBar.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "Update didn't work.", Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}