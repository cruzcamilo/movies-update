package com.example.trendingmovies.screens.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.trendingmovies.R
import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.screens.mvcviews.BaseViewMvc

class MovieListViewMvcImpl(inflater: LayoutInflater, container: ViewGroup?) :
    BaseViewMvc<MovieListViewMvc.Listener>(), MovieListViewMvc {

    private val recyclerView: RecyclerView
    private val emptyTextview: TextView
    private val mMoviesAdapter: MovieAdapter
    private val loadingSpinner: ProgressBar
    init {
        setRootView(inflater.inflate(R.layout.activity_movie_list, container, false))
        recyclerView = findViewById(R.id.recyclerview)
        emptyTextview = findViewById(R.id.tv_empty)
        loadingSpinner = findViewById(R.id.loading_spinner)

        mMoviesAdapter = MovieAdapter(object : OnMovieClickListener{
            override fun onMovieClicked(movie: Movie) {
                for (listener: MovieListViewMvc.Listener in getListeners()){
                    listener.onMovieClicked(movie)
                }
            }
        })

//        recyclerView.emptyView = emptyTextview
        recyclerView.adapter = mMoviesAdapter
        recyclerView.layoutManager = GridLayoutManager(getContext(),2)
    }

    override fun bindMovies(movies: List<Movie>) {
        mMoviesAdapter.bindData(movies)
        if (movies.isNotEmpty()){
            emptyTextview.visibility = View.GONE
            loadingSpinner.visibility = View.GONE
        }
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movie: Movie)
    }

    class MovieAdapter(onMovieClickListener: OnMovieClickListener) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

        private var mOnMovieClickListener = onMovieClickListener
        private var mMovieList = ArrayList<Movie>(0)

        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
        }

        fun bindData(movies: List<Movie>) {
            mMovieList = ArrayList(movies)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_list_item, parent, false)

            return MovieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                mOnMovieClickListener.onMovieClicked(mMovieList[position])

            }

            val URL = "https://image.tmdb.org/t/p/w185" + mMovieList[position].thumbnail

            Glide.with(holder.itemView.context)
                .load(URL)
                .apply(RequestOptions()
                    .override(540, 840)
                    .centerCrop())
                .into(holder.moviePoster)
        }

        override fun getItemCount(): Int { return mMovieList.size }
    }
}