package com.example.movietvartist.presentation

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.movietvartist.R
import com.example.movietvartist.databinding.ActivityMainBinding
import com.example.movietvartist.presentation.actor.ActorActivity
import com.example.movietvartist.presentation.movie.MovieActivity
import com.example.movietvartist.presentation.tvshow.TvShowActivity

/**
 * Home activity to start going to other activities.
 */
class HomeActivity : AppCompatActivity() {

    /**
     * Binding variable for our Activity.
     */

    private lateinit var binding: ActivityMainBinding
    var customProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        alertDialogFunction()

        /**
         * Set click listeners to go to other activities using our buttons.
         *
         * And we also implement animations here!
         */

        /*
        Use movie button to go to movie activity.
         */
        binding.apply {
            movieButton.setOnClickListener {
                val intent = Intent(this@HomeActivity, MovieActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this@HomeActivity,
                    "Going to Movies!", Toast.LENGTH_LONG
                ).show()
                overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_left)
            }

            /*
            Use tv button to Go to TvShow Activity.
             */

            tvshowButton.setOnClickListener {
                val intent = Intent(this@HomeActivity, TvShowActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this@HomeActivity,
                    "Going to TvShow!", Toast.LENGTH_LONG
                ).show()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
            }

            /*
            Use actor button to Go to Actor Activity.
             */

            actorButton.setOnClickListener {
                val intent = Intent(this@HomeActivity, ActorActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this@HomeActivity,
                    "Going to Actor!", Toast.LENGTH_LONG
                ).show()
                overridePendingTransition(R.anim.open_entry, R.anim.close_entry)
            }

        }
    }

    // method to show the progress dialog
    fun showProgressDialog() {
        binding.apply {
            customProgressDialog = Dialog(this@HomeActivity)
            customProgressDialog?.setContentView(R.layout.custom_progress_dialog)
            customProgressDialog?.show()
        }
    }

    // method to cancel the progress dialog
    fun cancelProgressDialog() {
        if (customProgressDialog != null)
            customProgressDialog?.dismiss()
        customProgressDialog = null
    }

    // Method to create an alert Dialog box
    private fun alertDialogFunction() {
        // Use the Builder class to assign to a variable for construction
        val builder = AlertDialog.Builder(this)
        // sets the title for the Alert Dialog box
        builder.setTitle("ALERT")
        // sets the message
        builder.setMessage("Do you think you will love this app?")
        // sets the icon for the dialog
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        // performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Toast.makeText(
                applicationContext,
                "I figured you would. =)", Toast.LENGTH_LONG
            ).show()
            dialogInterface.dismiss() // dialog will be dismissed
        }

        // performing cancel action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(
                applicationContext,
                "click cancel\n operation cancel", Toast.LENGTH_LONG
            ).show()
            dialogInterface.dismiss()
        }

        // performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(
                applicationContext,
                "ERRRRROORRR.. You are being dishonest!", Toast.LENGTH_LONG
            ).show()
            dialogInterface.dismiss()
        }

        // Once we finish setting up dialog, we Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // set Other dialog properties
        alertDialog.setCancelable(false) // won't allow user to cancel
        alertDialog.show() // show the dialog to the UI
    }

}