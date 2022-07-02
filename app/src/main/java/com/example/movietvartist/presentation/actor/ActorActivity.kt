package com.example.movietvartist.presentation.actor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movietvartist.R
import com.example.movietvartist.databinding.ActivityActorBinding

/**
 * Actor activity.
 */
class ActorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_actor)
    }
}