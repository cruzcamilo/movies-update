package com.example.trendingmovies.screens.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trendingmovies.Constants.IMAGE_BASE_URL
import com.example.trendingmovies.R
import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.screens.common.ImageLoader
import com.example.trendingmovies.screens.common.mvcviews.BaseViewMvc


class MovieListViewMvcImpl(
    inflater: LayoutInflater,
    container: ViewGroup?,
    val imageLoader: ImageLoader
) :
    BaseViewMvc<MovieListViewMvc.Listener>(), MovieListViewMvc {

    private val recyclerView: RecyclerView
    private val emptyTextview: TextView
    private val mMoviesAdapter: MovieAdapter
    private val loadingSpinner: ProgressBar
    private var pageNumber = 1

    init {
        setRootView(inflater.inflate(R.layout.activity_movie_list, container, false))
        recyclerView = findViewById(R.id.recyclerview)
        emptyTextview = findViewById(R.id.tv_empty)
        loadingSpinner = findViewById(R.id.loading_spinner)

        mMoviesAdapter = MovieAdapter(object : OnMovieClickListener {
            override fun onMovieClicked(movie: Movie) {
                for (listener: MovieListViewMvc.Listener in getListeners()) {
                    listener.onMovieClicked(movie)
                }
            }
        }, imageLoader)

//        recyclerView.emptyView = emptyTextview
        recyclerView.adapter = mMoviesAdapter
        val gridLayoutManager = GridLayoutManager(getContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(gridLayoutManager){
            override fun onLoadMore(currentPage: Int) {
                for (listener: MovieListViewMvc.Listener in getListeners()) {
                    listener.getMoreMovies(currentPage)
                }
            }
        })
    }

    override fun bindMovies(movies: List<Movie>) {
        mMoviesAdapter.bindData(movies)
        if (movies.isNotEmpty()) {
            emptyTextview.visibility = View.GONE
            loadingSpinner.visibility = View.GONE
        }
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movie: Movie)
    }

    class MovieAdapter(onMovieClickListener: OnMovieClickListener, val imageLoader: ImageLoader) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

        private var mOnMovieClickListener = onMovieClickListener
        private var mMovieList = ArrayList<Movie>(0)

        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
        }

        fun bindData(movies: List<Movie>) {
            mMovieList.addAll(movies)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_movie_list_item, parent, false)

            return MovieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                mOnMovieClickListener.onMovieClicked(mMovieList[position])
            }
            imageLoader.loadImage(
                IMAGE_BASE_URL + mMovieList[position].thumbnail,
                imageLoader.getMoviePosterOptions(),
                holder.moviePoster
            )
        }

        override fun getItemCount(): Int {
            return mMovieList.size
        }
    }
}