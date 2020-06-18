package com.example.trendingmovies.screens.movielist

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

//Taken from: https://stackoverflow.com/questions/51433106
abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {
    private var previousTotal =
        0 // The total number of items in the dataset after the last load
    private var loading =
        true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold =
        2 // The minimum amount of items to have below your current scroll position before loading more.
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private var current_page = 1
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount
            <= firstVisibleItem + visibleThreshold && firstVisibleItem!=-1
        ) {
            // End has been reached

            // Do something
            current_page++
            onLoadMore(current_page)
            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)
}