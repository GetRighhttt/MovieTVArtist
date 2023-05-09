package com.example.movietvartist.presentation

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

        /*
      Hide action bar and theme(OPTIONAL).
       */
//        supportActionBar?.hide()
//        this.window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
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
                showProgressDialog()
                val intent = Intent(this@HomeActivity, MovieActivity::class.java)
                startActivity(intent).also { cancelProgressDialog() }
                overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_left)
            }

            /*
            Use tv button to Go to TvShow Activity.
             */

            tvshowButton.setOnClickListener {
                showProgressDialog()
                val intent = Intent(this@HomeActivity, TvShowActivity::class.java)
                startActivity(intent).also { cancelProgressDialog() }
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
            }

            /*
            Use actor button to Go to Actor Activity.
             */

            actorButton.setOnClickListener {
                showProgressDialog()
                val intent = Intent(this@HomeActivity, ActorActivity::class.java)
                startActivity(intent).also { cancelProgressDialog() }
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

    private fun alertDialogFunction() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ALERT")
        builder.setMessage("Do you think you will love this app?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") { dialogInterface, _ ->
            Toast.makeText(
                applicationContext,
                "I knew it!", Toast.LENGTH_SHORT
            ).show()
            dialogInterface.dismiss() // dialog will be dismissed
        }
        builder.setNeutralButton("Cancel") { dialogInterface, _ ->
            Toast.makeText(
                applicationContext,
                "click cancel\n operation cancel", Toast.LENGTH_SHORT
            ).show()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("No") { dialogInterface, _ ->
            Toast.makeText(
                applicationContext,
                "ERRRRROORRR.. You are being dishonest!", Toast.LENGTH_LONG
            ).show()
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false) // won't allow user to cancel
        alertDialog.show() // show the dialog to the UI
    }

}