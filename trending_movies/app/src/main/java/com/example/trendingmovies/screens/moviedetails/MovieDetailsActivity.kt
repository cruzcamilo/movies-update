package com.example.trendingmovies.screens.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.R

class MovieDetailsActivity : AppCompatActivity() {

    private val mMovieId: String? by lazy{
        intent.extras?.getString(EXTRA_QUESTION_ID)
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, movieId: String){
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, movieId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        Toast.makeText(this, mMovieId, Toast.LENGTH_SHORT).show();
    }

}
