package com.example.movietvartist.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movietvartist.R
import com.example.movietvartist.databinding.ActivityTvShowBinding

/**
 * TvShow Activity.
 */
class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
    }
}