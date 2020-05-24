package com.example.trendingmovies.screens.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trendingmovies.R
import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.screens.mvcviews.BaseViewMvc

class MovieListViewMvcImpl(inflater: LayoutInflater, container: ViewGroup?) :
    BaseViewMvc<MovieListViewMvc.Listener>(), MovieListViewMvc {

    private val gridView: GridView
    private val emptyTextview: TextView

    init {
        setRootView(inflater.inflate(R.layout.activity_movie_list, container, false))
        gridView = findViewById(R.id.gridview)
        emptyTextview = findViewById(R.id.tv_empty)
        gridView.emptyView = emptyTextview
    }



    override fun bindMovie(movie: Movie): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movie: Movie)
    }

    class MovieAdapter(onMovieClickListener: OnMovieClickListener) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

        private var mOnMovieClickListener = onMovieClickListener
        private var mMovieList = ArrayList<Movie>(0)

        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            //TODO: Implement logic
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
        }

        override fun getItemCount(): Int { return mMovieList.size }
    }

}